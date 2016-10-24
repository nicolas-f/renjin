/**
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-2016 BeDataDriven Groep B.V. and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, a copy is available at
 * https://www.gnu.org/licenses/gpl-2.0.txt
 */
package org.renjin.eval.specialization;

import org.renjin.eval.ClosureDispatcher;
import org.renjin.eval.Context;
import org.renjin.sexp.*;


public class PosCall extends CallSpecialization {

  private FunctionCall call;
  private SEXP[] arguments;
  private Closure closure;
  private Symbol[] formals;
  private SEXP[] defaultValues;

  public PosCall(FunctionCall call, Closure closure) {
    super(closure);
    this.call = call;
    this.closure = closure;

    arguments = new SEXP[call.getArguments().length()];

    int argumentIndex = 0;
    for (SEXP argument : call.getArguments().values()) {
      arguments[argumentIndex++] = argument;
    }

    int formalCount = closure.getFormals().length();
    formals = new Symbol[formalCount];
    defaultValues = new SEXP[formalCount];

    int formalIndex = 0;
    for (PairList.Node formalNode : closure.getFormals().nodes()) {
      formals[formalIndex] = formalNode.getTag();
      defaultValues[formalIndex] = formalNode.getValue();
      formalIndex++;
    }
  }

  @Override
  public SEXP evaluate(Context callingContext, Environment callingEnvironment) {


    SEXP[] promisedArguments = new SEXP[arguments.length];
    PairList head = Null.INSTANCE;
    PairList.Node tail = null;
    for (int i = 0; i < arguments.length; i++) {
      SEXP promised;
      SEXP argument =  arguments[i];
      if(argument == Symbol.MISSING_ARG) {
        promised = Symbol.MISSING_ARG;
      } else if(argument instanceof Promise) {
        promised = argument;
      } else {
        promised = Promise.repromise(callingEnvironment, argument);
      }

      promisedArguments[i] = promised;

      PairList.Node node = new PairList.Node(Null.INSTANCE, promised, Null.INSTANCE);
      if(tail == null) {
        head = node;
        tail = node;
      } else {
        tail.setNextNode(node);
        tail = node;
      }
    }


    Context functionContext = callingContext.beginFunction(callingEnvironment, call, closure, head);
    Environment functionEnvironment = functionContext.getEnvironment();

    for (int i = 0; i < formals.length; i++) {
      SEXP value;
      if(i < arguments.length) {
        value = promisedArguments[i];
      } else {
        // Not provided
        value = defaultValues[i];
        if(value != Symbol.MISSING_ARG) {
          value = Promise.promiseMissing(functionEnvironment, value);
        }
      }
      functionEnvironment.setVariable(formals[i], value);
    }

    return ClosureDispatcher.evalClosure(closure, functionContext, functionEnvironment);
  }
}
