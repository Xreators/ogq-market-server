package com.ogqcorp.market.domain.content.artwork;

import com.ogqcorp.market.common.type.artwork.Tag;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 3:35 오후 Last Modified At: 2020/12/09
 */
@Embeddable
public class ArtworkTag {
  private String value;

  public ArtworkTag() {}
  private ArtworkTag(final List<String> values) {
    value = values.stream().collect(Collectors.joining(","));
  }
  public List<Tag> toTags() {
    return Arrays.asList(value.split(",")).stream()
      .map(Tag::fromString)
      .collect(Collectors.toUnmodifiableList());
  }

  public static ArtworkTag fromStrings(String...values) {
    return new ArtworkTag(Arrays.asList(values));
  }
  public static ArtworkTag fromStrings(List<String> values) {
    return new ArtworkTag(values);
  }
  public static ArtworkTag fromTagList(List<Tag> tags) {
    return Optional.ofNullable(tags)
      .map(it -> new ArtworkTag(it.stream().map(Tag::tagString).collect(Collectors.toList())))
      .orElseGet(() -> fromStrings(""))
      ;  // not unmodifiable
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkTag)) {
      return false;
    }
    ArtworkTag that = (ArtworkTag) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return Optional.ofNullable(value).orElseGet(() -> "NULL");
  }
}
