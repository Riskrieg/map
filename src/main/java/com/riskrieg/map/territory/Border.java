/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
