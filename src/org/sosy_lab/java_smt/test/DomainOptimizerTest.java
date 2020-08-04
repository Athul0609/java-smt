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

package org.sosy_lab.java_smt.test;


import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.SolverContextFactory;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.IntegerFormulaManager;
import org.sosy_lab.java_smt.api.NumeralFormula.IntegerFormula;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.SolverException;
import org.sosy_lab.java_smt.domain_optimization.DomainOptimizerProverEnvironment;
import org.sosy_lab.java_smt.domain_optimization.DomainOptimizerSolverContext;

public class DomainOptimizerTest {

  private boolean isUnsatWithoutDomainOptimizer;
  private boolean isUnsatWithDomainOptimizer;

  @Before
  public void setupTest()
      throws InvalidConfigurationException, InterruptedException, SolverException {
    Configuration config = Configuration.builder().setOption("useDomainOptimizer", "true").build();
    LogManager logger = BasicLogManager.create(config);
    ShutdownManager shutdown = ShutdownManager.create();
    List<Formula> constraints = new ArrayList<>();
    List<Formula> queries = new ArrayList<>();
    DomainOptimizerSolverContext delegate =
        (DomainOptimizerSolverContext) SolverContextFactory.createSolverContext(
            config, logger, shutdown.getNotifier(), Solvers.SMTINTERPOL);
    try (ProverEnvironment basicEnv = delegate.newProverEnvironment()) {

      FormulaManager fmgr = delegate.getFormulaManager();

      IntegerFormulaManager imgr = fmgr.getIntegerFormulaManager();
      IntegerFormula x = imgr.makeVariable("x");
      IntegerFormula y = imgr.makeVariable("y");
      IntegerFormula z = imgr.makeVariable("z");
      BooleanFormula constraintOne = imgr.lessOrEquals(x, imgr.makeNumber(7));
      BooleanFormula constraintTwo = imgr.lessOrEquals(imgr.makeNumber(4), x);
      BooleanFormula constraintThree =
          imgr.lessOrEquals(imgr.subtract(y, imgr.makeNumber(3)), imgr.makeNumber(7));
      BooleanFormula constraintFour =
          imgr.greaterOrEquals(imgr.add(y, imgr.makeNumber(3)), imgr.makeNumber(3));
      BooleanFormula constraintFive = imgr.lessOrEquals(imgr.add(z, y), imgr.makeNumber(5));
      BooleanFormula constraintSix =
          imgr.lessOrEquals(imgr.add(y, imgr.makeNumber(4)), imgr.add(x, imgr.makeNumber(5)));
      BooleanFormula constraintSeven =
          imgr.greaterOrEquals(
              imgr.add(imgr.add(z, imgr.makeNumber(3)), imgr.makeNumber(2)),
              imgr.makeNumber(-50));
      constraints.add(constraintOne);
      constraints.add(constraintTwo);
      constraints.add(constraintThree);
      constraints.add(constraintFour);
      constraints.add(constraintFive);
      constraints.add(constraintSix);
      constraints.add(constraintSeven);

      BooleanFormula queryOne = imgr.greaterThan(imgr.add(x, imgr.makeNumber(7)), z);
      BooleanFormula queryTwo = imgr.lessOrEquals(imgr.subtract(y, z), imgr.makeNumber(8));
      BooleanFormula queryThree = imgr.lessThan(imgr.add(x, y), imgr.makeNumber(100));
      queries.add(queryOne);
      queries.add(queryTwo);
      queries.add(queryThree);

      for (Formula constraint : constraints) {
        basicEnv.addConstraint((BooleanFormula) constraint);
      }
      for (Formula query : queries) {
        basicEnv.addConstraint((BooleanFormula) query);
      }
      isUnsatWithoutDomainOptimizer = basicEnv.isUnsat();
    }
      try (DomainOptimizerProverEnvironment env = new DomainOptimizerProverEnvironment(delegate)) {
        for (Formula constraint : constraints) {
          env.addConstraint((BooleanFormula) constraint);
        }
        for (Formula query : queries) {
          env.pushQuery(query);
        }
        boolean isUnsat = env.isUnsat();
        isUnsatWithDomainOptimizer = isUnsat;
      }
    delegate.close();
  }

  @Test
  public void isSatisfiabilityAffected() {
    assertEquals(isUnsatWithDomainOptimizer, isUnsatWithoutDomainOptimizer);
  }

}
