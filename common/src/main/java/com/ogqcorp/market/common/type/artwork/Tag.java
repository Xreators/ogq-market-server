package com.ogqcorp.market.common.type.artwork;

import java.util.Objects;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 4:14 오후 Last Modified At: 2020/12/08
 */
public class Tag {
  private String value;

  public Tag() {
    // Needed by Hibernate
  }
  public Tag(final String value) {
    this.value = value;
  }
  public static Tag fromString(String value) {
    return new Tag(value);
  }

  public String tagString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Tag)) return false;
    Tag tag = (Tag) o;
    return Objects.equals(value, tag.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }

}
