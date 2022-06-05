/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
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
