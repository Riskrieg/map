/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
 */

package com.riskrieg.map.metadata;

import java.util.Objects;

public record Alignment(Alignment.Vertical vertical, Alignment.Horizontal horizontal) {

  public Alignment {
    Objects.requireNonNull(vertical);
    Objects.requireNonNull(horizontal);
  }

  public enum Vertical {
    TOP,
    MIDDLE,
    BOTTOM
  }

  public enum Horizontal {
    LEFT,
    CENTER,
    RIGHT
  }

}
