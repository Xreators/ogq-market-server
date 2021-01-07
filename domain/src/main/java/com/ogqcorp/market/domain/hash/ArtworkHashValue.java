package com.ogqcorp.market.domain.hash;

import java.util.Objects;
import java.util.Optional;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:23 오후 Last Modified At: 2020/12/17
 */
@Embeddable
public class ArtworkHashValue {
  private String value;

  public ArtworkHashValue() {}
  public ArtworkHashValue(final String value) {
    this.value = value;
  }
  public static ArtworkHashValue fromString(String value) {
    return new ArtworkHashValue(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkHashValue)) {
      return false;
    }
    ArtworkHashValue ArtworkHashValue = (ArtworkHashValue) o;
    return Objects.equals(value, ArtworkHashValue.value);
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
