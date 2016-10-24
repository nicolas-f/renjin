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


import org.renjin.eval.Context;
import org.renjin.sexp.Environment;
import org.renjin.sexp.Function;
import org.renjin.sexp.SEXP;

public abstract class CallSpecialization {

  public static final CallSpecialization NONE = new CallSpecialization(null) {
    @Override
    public SEXP evaluate(Context callingContext, Environment callingEnvironment) {
      return null;
    }
  };

  private final Function function;

  public CallSpecialization(Function function) {
    this.function = function;
  }

  public boolean matches(Function resolvedFunction) {
    return resolvedFunction == function;
  }

  public abstract SEXP evaluate(Context callingContext, Environment callingEnvironment);

}
