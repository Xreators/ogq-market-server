package com.ogqcorp.market.domain.content.artwork;

import com.ogqcorp.market.common.type.artwork.TextContent;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 3:45 오후 Last Modified At: 2020/12/09
 */
@Embeddable
public class ArtworkTextContent {
  @Column(length = 255, nullable = false)
  private String title;
  @Column(columnDefinition = "TEXT")
  private String description;

  public ArtworkTextContent() {}
  private ArtworkTextContent(TextContent textContent) {
    this.title = textContent.getTitle();
    this.description = textContent.getDescription();
  }
  public static ArtworkTextContent fromTextContent(TextContent textContent) {
    return new ArtworkTextContent(textContent);
  }
  public String title() {
    return title;
  }
  public String description() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkTextContent)) {
      return false;
    }
    ArtworkTextContent that = (ArtworkTextContent) o;
    return Objects.equals(title, that.title) && Objects
        .equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description);
  }

  @Override
  public String toString() {
    return "ArtworkTextContent{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      '}';
  }
}
