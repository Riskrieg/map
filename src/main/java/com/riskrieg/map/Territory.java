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

package com.riskrieg.map;

import com.riskrieg.map.territory.Nucleus;
import com.riskrieg.map.territory.TerritoryIdentity;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public record Territory(TerritoryIdentity identity, Set<Nucleus> nuclei) {

  public Territory {
    Objects.requireNonNull(identity);
    Objects.requireNonNull(nuclei);
    if (nuclei.isEmpty()) {
      throw new IllegalStateException("Set<Nucleus> 'nuclei' must not be empty");
    }
    nuclei = Set.copyOf(nuclei);
  }

  public Territory(TerritoryIdentity identity, Nucleus nucleus) {
    this(identity, Collections.singleton(nucleus));
  }

  @Override
  public Set<Nucleus> nuclei() {
    return Set.copyOf(nuclei);
  }

  public String name() {
    return identity.toString();
  }

}
