package com.ogqcorp.market.domain.content.artwork;

import com.ogqcorp.market.common.type.UniqueId;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 3:30 오후 Last Modified At: 2020/12/09
 */
@Embeddable
public class ArtworkId {
  private String value;

  public ArtworkId() {}
  private ArtworkId(final String value) {
    this.value = value;
  }
  public static ArtworkId fromString(String value) {
    return new ArtworkId(value);
  }
  public static ArtworkId fromUniqueId(UniqueId id) {
    return new ArtworkId(id.idString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkId)) {
      return false;
    }
    ArtworkId artworkId = (ArtworkId) o;
    return Objects.equals(value, artworkId.value);
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
