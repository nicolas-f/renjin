/*
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-2018 BeDataDriven Groep B.V. and contributors
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
package org.renjin.gcc.runtime;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StdlibTest {

  @Test
  public void ctime() {
    IntPtr time = new IntPtr(0);
    Stdlib.time(time);
    BytePtr str = Stdlib.ctime(time);

    System.out.println(str.nullTerminatedString());
  }

  @Test
  public void lroundf() {

    assertThat(Stdlib.lroundf( 2.3f), equalTo(2L));
    assertThat(Stdlib.lroundf( 2.5f), equalTo(3L));
    assertThat(Stdlib.lroundf( 2.7f), equalTo(3L));
    assertThat(Stdlib.lroundf(-2.3f), equalTo(-2L));
    assertThat(Stdlib.lroundf(-2.5f), equalTo(-3L));
    assertThat(Stdlib.lroundf(-2.7f), equalTo(-3L));
    assertThat(Stdlib.lroundf(-0), equalTo(0L));
    assertThat(Stdlib.lroundf(Float.NEGATIVE_INFINITY), equalTo(Long.MIN_VALUE));
    assertThat(Stdlib.lroundf(Float.POSITIVE_INFINITY), equalTo(Long.MAX_VALUE));

    assertThat(Stdlib.lroundf(Long.MAX_VALUE+1.5f), equalTo(Long.MAX_VALUE));
  }

}