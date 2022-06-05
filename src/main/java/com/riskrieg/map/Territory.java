/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
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
