/*
 *     Riskrieg, an open-source conflict simulation game.
 *     Copyright (C) 2020-2022 Aaron Yoder <aaronjyoder@gmail.com> and the Riskrieg contributors
 *
 *     This code is licensed under the MIT license.
 */

package com.riskrieg.map.territory;

import java.awt.Point;

public record Nucleus(int x, int y) {

  public Nucleus() {
    this(0, 0);
  }

  public Point toPoint() {
    return new Point(x, y);
  }

}
