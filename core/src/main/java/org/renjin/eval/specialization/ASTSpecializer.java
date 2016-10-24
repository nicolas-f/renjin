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

import org.renjin.sexp.*;

/**
 * Creates CallSpecializers for AST nodes
 */
public class ASTSpecializer {

  public static CallSpecialization specialize(FunctionCall call, Function resolvedFunction) {
    if(resolvedFunction instanceof Closure) {

      // profile the arguments to see what we're dealing with
      boolean namedArguments = false;
      boolean ellipsesProvided = false;
      boolean ellipsesFormal = false;
      int argCount = 0;
      int formalCount = 0;
      for (PairList.Node node : call.nodes()) {
        if(node.hasName()) {
          namedArguments = true;
        }
        if(node.getValue() == Symbols.ELLIPSES) {
          ellipsesProvided = true;
        }
        argCount++;
      }

      for (PairList.Node node : ((Closure) resolvedFunction).getFormals().nodes()) {
        if(node.getTag() == Symbols.ELLIPSES) {
          ellipsesFormal = true;
        }
        formalCount ++;
      }

      if(!namedArguments && !ellipsesProvided && !ellipsesFormal && (argCount <= formalCount)) {
        return new PosCall(call, (Closure) resolvedFunction);
      }
    }
    return CallSpecialization.NONE;
  }

}
