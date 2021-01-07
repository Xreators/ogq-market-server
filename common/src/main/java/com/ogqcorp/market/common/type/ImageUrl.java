package com.ogqcorp.market.common.type;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 4:26 오후 Last Modified At: 2020/12/08
 */
public class ImageUrl {
  private String value;

  public ImageUrl() {}
  public ImageUrl(final String value) {
    this.value = value;
  }
  public static ImageUrl fromString(String value) {
    return new ImageUrl(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ImageUrl)) return false;
    ImageUrl v2  = (ImageUrl) o;
    return Objects.equals(value, v2.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return Optional.ofNullable(value).map(String::toString).orElseGet(() -> "NULL");
  }

}
