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
package org.renjin.gcc.runtime;

import java.lang.invoke.MethodHandle;

/**
 * Wraps a record unit pointer as a Ptr
 */
public class RecordUnitPtr<T> implements Ptr {

  private T record;


  public RecordUnitPtr(T record) {
    this.record = record;
  }

  public T get() {
    return record;
  }

  @Override
  public Object getArray() {
    return record;
  }

  @Override
  public int getOffset() {
    return 0;
  }

  @Override
  public int getOffsetInBytes() {
    return 0;
  }

  @Override
  public Ptr realloc(int newSizeInBytes) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Ptr pointerPlus(int bytes) {
    if(bytes == 0) {
      return this;
    }
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public boolean getBoolean() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public boolean getBoolean(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setBoolean(int offset, boolean value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setBoolean(boolean value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public byte getByte() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public byte getByte(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setByte(byte value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setByte(int offset, byte value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public short getShort() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public short getShort(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setShort(short value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setShort(int offset, short value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public char getChar() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public char getChar(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setChar(char value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setChar(int offset, char value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public double getDouble() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public double getDouble(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public double getAlignedDouble(int index) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setDouble(double value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setDouble(int offset, double value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public float getFloat() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public float getFloat(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setFloat(float value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setFloat(int offset, float value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getInt() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getInt(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int getIntAligned(int index) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setInt(int value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setInt(int offset, int value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public long getLong() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public long getLong(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setLong(long value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setLong(int offset, long value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Ptr getPointer() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Ptr getPointer(int offset) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setPointer(Ptr value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void setPointer(int offset, Ptr value) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int toInt() {
    return System.identityHashCode(record);
  }

  @Override
  public void memset(int byteValue, int n) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void memcpy(Ptr source, int numBytes) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public void memmove(Ptr source, int numBytes) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int memcmp(Ptr other, int numBytes) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Ptr copyOf(int offset, int numBytes) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public Ptr copyOf(int numBytes) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public MethodHandle toMethodHandle() {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public int compareTo(Ptr o) {
    if(record == o.getArray()) {
      return 0;
    }
    return Integer.compare(toInt(), o.toInt());
  }
}
