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

import com.riskrieg.map.metadata.Alignment;
import com.riskrieg.map.metadata.Availability;
import com.riskrieg.map.metadata.Flavor;
import java.util.Objects;

public record RkmMetadata(Flavor flavor, Availability availability, Alignment alignment, boolean autogenTitle) {

  public RkmMetadata {
    Objects.requireNonNull(flavor);
    Objects.requireNonNull(availability);
    Objects.requireNonNull(alignment);
  }

  public RkmMetadata() {
    this(Flavor.UNKNOWN, Availability.UNAVAILABLE, new Alignment(Alignment.Vertical.BOTTOM, Alignment.Horizontal.LEFT), true);
  }

  public RkmMetadata(Flavor flavor, Availability availability, Alignment alignment) {
    this(flavor, availability, alignment, true);
  }

  public RkmMetadata withFlavor(Flavor flavor) {
    return new RkmMetadata(flavor, availability, alignment, autogenTitle);
  }

  public RkmMetadata withAvailability(Availability availability) {
    return new RkmMetadata(flavor, availability, alignment, autogenTitle);
  }

  public RkmMetadata withAlignment(Alignment alignment) {
    return new RkmMetadata(flavor, availability, alignment, autogenTitle);
  }

  public RkmMetadata withAutogenTitle(boolean shouldAutogenTitle) {
    return new RkmMetadata(flavor, availability, alignment, shouldAutogenTitle);
  }

}
