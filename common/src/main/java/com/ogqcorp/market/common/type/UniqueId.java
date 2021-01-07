package com.ogqcorp.market.common.type;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 3:41 오후 Last Modified At: 2020/12/08
 */
public class UniqueId {
  private String id;

  public UniqueId() {}
  public UniqueId(final String id) {
    this.id = id;
  }
  public static UniqueId fromString(String id) {
    return new UniqueId(id);
  }

  public String idString() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UniqueId)) {
      return false;
    }
    UniqueId uniqueId = (UniqueId) o;
    return Objects.equals(id, uniqueId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return Optional.ofNullable(id).map(String::toString).orElseGet(() -> "NULL");
  }
}
