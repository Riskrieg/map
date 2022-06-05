/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
 */

package com.riskrieg.map.territory;

import java.util.Objects;

public final class TerritoryIdentity {

  private final String value;

  public TerritoryIdentity(String value) {
    Objects.requireNonNull(value);
    if (value.isBlank()) {
      throw new IllegalStateException("String 'value' cannot be blank");
    }
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (TerritoryIdentity) obj;
    return Objects.equals(this.value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }


}
