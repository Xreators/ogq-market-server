package com.ogqcorp.market.domain.account;

import com.ogqcorp.market.common.type.ImageUrl;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 2:27 오후 Last Modified At: 2020/12/09
 */
@Embeddable
public class ProfileUrl {
  private String value;

  public ProfileUrl() {}
  private ProfileUrl(final String value) {
    this.value = value;
  }
  public static ProfileUrl fromString(final String value) {
    return new ProfileUrl(value);
  }
  public static ProfileUrl fromImageUrl(final ImageUrl url) {
    return new ProfileUrl(url.toString());
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProfileUrl)) {
      return false;
    }
    ProfileUrl that = (ProfileUrl) o;
    return Objects.equals(value, that.value);
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
