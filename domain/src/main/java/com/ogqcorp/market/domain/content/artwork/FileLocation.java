package com.ogqcorp.market.domain.content.artwork;

import com.ogqcorp.market.common.type.ImageUrl;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 4:12 오후 Last Modified At: 2020/12/09
 */
@Embeddable
public class FileLocation {
  private String value;

  public FileLocation() {}
  private FileLocation(final String value) {
    this.value = Optional.ofNullable(value).orElseGet(() -> "");
  }
  public static FileLocation fromString(final String value) {
    return new FileLocation(value);
  }
  public static FileLocation fromImageUrl(final ImageUrl url) {
    return Optional.ofNullable(url)
      .map(it -> new FileLocation(url.toString()))
      .orElseGet(() -> new FileLocation(""));
  }

  public String location(String endPoint) {
    if(StringUtils.isEmpty(value)) {
      return null;
    }
    if(isUrl()) {
      return value;
    }
    return endPoint + "/" + value;
  }
  public String fileKey() {
    return this.value;
  }

  private boolean isUrl() {
    return value.startsWith("http");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FileLocation)) {
      return false;
    }
    FileLocation that = (FileLocation) o;
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
