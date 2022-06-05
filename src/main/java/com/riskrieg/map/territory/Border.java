/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
 */

package com.riskrieg.map.territory;

import java.util.Objects;

public record Border(TerritoryIdentity source, TerritoryIdentity target) {

  public Border {
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);
    if (source.equals(target)) {
      throw new IllegalStateException("source and target cannot be equal");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Border border = (Border) o;
    return (source.equals(border.source) && target.equals(border.target)) || (source.equals(border.target) && target.equals(border.source));
  }

  @Override
  public int hashCode() {
    int hash = 17;
    int hashMultiplier = 79;
    int hashSum = source.hashCode() + target.hashCode();
    hash = hashMultiplier * hash * hashSum;
    return hash;
  }

}
