/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
 */

package com.riskrieg.map.territory;

import java.util.Objects;

public record TerritoryIdentity(String rawValue) {

  public TerritoryIdentity {
    Objects.requireNonNull(rawValue);
    if (rawValue.isBlank()) {
      throw new IllegalStateException("String 'rawValue' cannot be blank");
    }
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
    return Objects.equals(this.rawValue, that.rawValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rawValue);
  }

  @Override
  public String toString() {
    return rawValue;
  }


}
