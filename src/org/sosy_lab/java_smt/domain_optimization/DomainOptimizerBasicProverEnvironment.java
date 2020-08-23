/*
 *  JavaSMT is an API wrapper for a collection of SMT solvers.
 *  This file is part of JavaSMT.
 *
 *  Copyright (C) 2007-2016  Dirk Beyer
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.sosy_lab.java_smt.domain_optimization;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.sosy_lab.java_smt.api.BasicProverEnvironment;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.BooleanFormulaManager;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.IntegerFormulaManager;
import org.sosy_lab.java_smt.api.Model;
import org.sosy_lab.java_smt.api.Model.ValueAssignment;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.SolverException;

class DomainOptimizerBasicProverEnvironment<T> implements BasicProverEnvironment<T> {

  private int totalConstraints = 0;
  private int solvedConstraints = 0;
  private final ProverEnvironment wrapped;
  private final DomainOptimizer opt;
  private final DomainOptimizerFormulaRegister register;
  private final FormulaManager fmgr;

  DomainOptimizerBasicProverEnvironment(DomainOptimizerSolverContext delegate) {
    this.fmgr = delegate.getFormulaManager();
    this.wrapped = delegate.newBasicProverEnvironment();
    opt = new BasicDomainOptimizer(wrapped, delegate);
    register = new DomainOptimizerFormulaRegister(opt);
  }

  @Override
  public void pop() {
    this.wrapped.pop();
  }

  public List<Formula> getVariables() {
    return this.opt.getVariables();
  }

  public Interval getInterval(Formula var) {
    return this.opt.getInterval(var);
  }

  @Override
  public T addConstraint(BooleanFormula constraint) throws InterruptedException, SolverException {
    if (this.register.countVariables(constraint) == 1) {
      if (!this.opt.fallBack(constraint)) {
        this.opt.pushConstraint(constraint);
      }
      this.wrapped.addConstraint(constraint);
    } else {
      this.totalConstraints += 1;
      if (this.register.countVariables(constraint) <= 17) {
        pushQuery(constraint);
      } else {
        this.wrapped.addConstraint(constraint);
      }
    }
    return null;
  }

  public int getTotalConstraints() {
    return this.totalConstraints;
  }

  public int getSolvedConstraints() {
    return this.solvedConstraints;
  }

  @Override
  public void push() {
    // TODO push Optimizer
    this.wrapped.push();
  }

  @Override
  public boolean isUnsat() throws SolverException, InterruptedException {
    // TODO check status of Optimizer
    return this.wrapped.isUnsat();
  }

  @Override
  public boolean isUnsatWithAssumptions(Collection<BooleanFormula> assumptions)
      throws SolverException, InterruptedException {
    return wrapped.isUnsatWithAssumptions(assumptions);
  }

  @Override
  public Model getModel() {
    Map<Formula, Interval> model = opt.getDomainDictionary();
    IntegerFormulaManager imgr = fmgr.getIntegerFormulaManager();
    BooleanFormulaManager bmgr = fmgr.getBooleanFormulaManager();
    List<ValueAssignment> assignments = new ArrayList<>();
    for (Formula f : model.keySet()) {
      for (Interval i : model.values()) {
        List<Formula> interpretation = new ArrayList<>();
        ValueAssignment assignmentLower =
            new ValueAssignment(
                f,
                imgr.makeNumber(i.getLowerBound()),
                bmgr.makeBoolean(f == imgr.makeNumber(i.getLowerBound())),
                f.toString() + "_low",
                imgr.makeNumber(i.getLowerBound()),
                interpretation);
        ValueAssignment assignmentUpper =
            new ValueAssignment(
                f,
                imgr.makeNumber(i.getUpperBound()),
                bmgr.makeBoolean(f == imgr.makeNumber(i.getUpperBound())),
                f.toString() + "_up",
                imgr.makeNumber(i.getUpperBound()),
                interpretation);
        assignments.add(assignmentLower);
        assignments.add(assignmentUpper);
      }
    }
    DomainOptimizerModel pModel = new DomainOptimizerModel();
    ImmutableList<ValueAssignment> assignmentsImmutable = ImmutableList.copyOf(assignments);
    pModel.setAssignments(assignmentsImmutable);
    return pModel;
  }

  @Override
  public List<BooleanFormula> getUnsatCore() {
    throw new UnsupportedOperationException("not yet implemented");
  }

  @Override
  public Optional<List<BooleanFormula>> unsatCoreOverAssumptions(
      Collection<BooleanFormula> assumptions) throws SolverException, InterruptedException {
    return wrapped.unsatCoreOverAssumptions(assumptions);
  }

  @Override
  public void close() {
    wrapped.close();
  }

  @Override
  public <R> R allSat(AllSatCallback<R> callback, List<BooleanFormula> important)
      throws InterruptedException, SolverException {
    // TODO we could implement this via extension of AbstractProverWithAllSat
    throw new UnsupportedOperationException("not yet implemented");
  }

  public ProverEnvironment getWrapped() {
    return this.wrapped;
  }

  public void pushQuery(Formula query) throws InterruptedException, SolverException {
    DomainOptimizerDecider decider = opt.getDecider();
    BooleanFormula substituted = decider.pruneTree(query, 500);
    if (decider.getFallback()) {
      this.wrapped.addConstraint((BooleanFormula) query);
    } else {
      this.solvedConstraints += 1;
      this.wrapped.addConstraint(substituted);
    }
  }

}
