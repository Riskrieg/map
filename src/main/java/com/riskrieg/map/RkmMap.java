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

import com.riskrieg.map.territory.Border;
import com.riskrieg.map.territory.TerritoryIdentity;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public record RkmMap(String codename, String displayName, String author,
                     Set<Territory> vertices, Set<Border> edges,
                     BufferedImage baseLayer,
                     BufferedImage textLayer) {

  public static final String CODENAME_REGEX = "^(?!-)[a-z\\d-]+[^-]$";

  public RkmMap {
    Objects.requireNonNull(codename);
    Objects.requireNonNull(displayName);
    Objects.requireNonNull(author);
    Objects.requireNonNull(vertices);
    Objects.requireNonNull(edges);
    Objects.requireNonNull(baseLayer);
    Objects.requireNonNull(textLayer);
    if (codename.isBlank()) {
      throw new IllegalArgumentException("String 'codename' cannot be blank");
    }
    if (!codename.matches(CODENAME_REGEX)) {
      throw new IllegalArgumentException("String 'codename' must match the regex '" + CODENAME_REGEX + "'");
    }
    if (displayName.isBlank()) {
      throw new IllegalArgumentException("String 'author' cannot be blank");
    }
    if (author.isBlank()) {
      throw new IllegalArgumentException("String 'author' cannot be blank");
    }
    vertices = Set.copyOf(vertices);
    edges = Set.copyOf(edges);
  }

  @Override
  public Set<Territory> vertices() {
    return Set.copyOf(vertices);
  }

  @Override
  public Set<Border> edges() {
    return Set.copyOf(edges);
  }

  public Optional<Territory> get(TerritoryIdentity identity) {
    Objects.requireNonNull(identity);
    return vertices.stream().filter(territory -> territory.identity().equals(identity)).findFirst();
  }

  public boolean contains(TerritoryIdentity identity) {
    Objects.requireNonNull(identity);
    return vertices.stream().anyMatch(territory -> territory.identity().equals(identity));
  }

  /**
   * Checks to see if two BufferedImages are equal.
   *
   * @param imageA the first image.
   * @param imageB the second image.
   * @return {@code true} if the images have exactly the same pixels, otherwise {@false}
   */
  private boolean imagesEqual(BufferedImage imageA, BufferedImage imageB) {
    if (imageA.getWidth() != imageB.getWidth() || imageA.getHeight() != imageB.getHeight()) {
      return false;
    }

    int width = imageA.getWidth();
    int height = imageA.getHeight();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (imageA.getRGB(x, y) != imageB.getRGB(x, y)) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RkmMap rkmMap = (RkmMap) o;
    return codename.equals(rkmMap.codename) && displayName.equals(rkmMap.displayName) && author.equals(rkmMap.author)
        && vertices.equals(rkmMap.vertices) && edges.equals(rkmMap.edges)
        && imagesEqual(baseLayer, rkmMap.baseLayer) && imagesEqual(textLayer, rkmMap.textLayer);
  }

  @Override
  public int hashCode() { // TODO: Hash baseLayer and textLayer
    return Objects.hash(codename, displayName, author, vertices, edges, baseLayer, textLayer);
  }

}
