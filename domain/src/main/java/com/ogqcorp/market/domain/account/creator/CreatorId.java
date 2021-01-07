package com.ogqcorp.market.domain.account.creator;

import com.ogqcorp.market.common.type.UniqueId;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 2:17 오후 Last Modified At: 2020/12/09
 */
@Embeddable
public class CreatorId {
  private String value;

  public CreatorId() {}
  public CreatorId(final String value) {
    this.value = value;
  }
  public static CreatorId fromString(String value) {
    return new CreatorId(value);
  }
  public static CreatorId fromUniqueId(UniqueId id) {
    return new CreatorId(id.idString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CreatorId)) {
      return false;
    }
    CreatorId creatorId = (CreatorId) o;
    return Objects.equals(value, creatorId.value);
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
