// This file is part of JavaSMT,
// an API wrapper for a collection of SMT solvers:
// https://github.com/sosy-lab/java-smt
//
// SPDX-FileCopyrightText: 2021 Dirk Beyer <https://www.sosy-lab.org>
//
// SPDX-License-Identifier: Apache-2.0

package org.sosy_lab.java_smt.solvers.cvc4;

import edu.stanford.CVC4.Expr;
import edu.stanford.CVC4.ExprManager;
import edu.stanford.CVC4.Kind;
import edu.stanford.CVC4.SmtEngine;
import edu.stanford.CVC4.Type;
import edu.stanford.CVC4.vectorExpr;
import java.util.List;
import org.sosy_lab.java_smt.api.SolverException;
import org.sosy_lab.java_smt.basicimpl.AbstractQuantifiedFormulaManager;
import org.sosy_lab.java_smt.basicimpl.FormulaCreator;

public class CVC4QuantifiedFormulaManager
    extends AbstractQuantifiedFormulaManager<Expr, Type, ExprManager, Expr> {

  private final ExprManager exprManager;

  protected CVC4QuantifiedFormulaManager(
      FormulaCreator<Expr, Type, ExprManager, Expr> pFormulaCreator) {
    super(pFormulaCreator);

    exprManager = pFormulaCreator.getEnv();
  }

  /*
   * (non-Javadoc) CVC4 supports this for LIA and LRA only. If it fails, we return the input. If you
   * try this on array theories, it will most likely run indefinetly.
   */
  @Override
  protected Expr eliminateQuantifiers(Expr pExtractInfo)
      throws SolverException, InterruptedException {
    SmtEngine smtEngine = new SmtEngine(exprManager);
    Expr eliminated = pExtractInfo;
    // The first bool is for partial or full elimination. true is full
    // The second bool (optional; 2 methods) is whether to output warnings or not, such as when an
    // unexpected logic is used.
    try {
      eliminated = smtEngine.doQuantifierElimination(pExtractInfo, true);
    } catch (RuntimeException e) {
      // quantifier elimination failed, simply return the input
    }
    // We don't delete it in the prover.close(), is there a reason for that?
    smtEngine.delete();
    return eliminated;
  }

  /*
   * Makes the quantifier entered in CVC4. Note that CVC4 uses bound variables in quantified
   * formulas instead of the normal free vars. We create a bound copy for every var and substitute
   * the free var for the bound var in the body Formula. Note that CVC4 uses their internal Lists
   * for the variable list in quantifiers.
   */
  @Override
  public Expr mkQuantifier(Quantifier pQ, List<Expr> pVars, Expr pBody) {
    if (pVars.isEmpty()) {
      throw new IllegalArgumentException("Empty variable list for quantifier.");
    } else {
      // CVC4 uses its own lists for quantifier that may only have bound vars
      vectorExpr vec = new vectorExpr();
      Expr substBody = pBody;
      // every free needs a bound copy. As the internal Id is different for every variable, even
      // with the same name, this is fine.
      for (Expr var : pVars) {
        Expr boundCopy = ((CVC4FormulaCreator) formulaCreator).makeBoundCopy(var);
        vec.add(boundCopy);
        substBody = substBody.substitute(var, boundCopy);
      }
      Expr quantifiedVars = exprManager.mkExpr(Kind.BOUND_VAR_LIST, vec);

      if (pQ == Quantifier.FORALL) {
        return exprManager.mkExpr(Kind.FORALL, quantifiedVars, substBody);
      } else {
        return exprManager.mkExpr(Kind.EXISTS, quantifiedVars, substBody);
      }
    }
  }
}
