package com.ogqcorp.market.common.type.artwork;

import java.util.Objects;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 4:03 오후 Last Modified At: 2020/12/08
 */
public class TextContent {
  private String title;
  private String description;

  private TextContent(){}
  public String getTitle() {
    return title;
  }
  public String getDescription() {
    return description;
  }
  public static Builder builder() {
    return new Builder();
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TextContent)) {
      return false;
    }
    TextContent that = (TextContent) o;
    return title.equals(that.title) && description.equals(that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description);
  }

  @Override
  public String toString() {
    return "ArtworkTextContentDTO{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      '}';
  }

  public static class Builder {
    private TextContent data;
    private Builder() {
      data = new TextContent();
    }
    public Builder title(String title) {
      data.title = title;
      return this;
    }
    public Builder description(String description) {
      data.description = description;
      return this;
    }
    public TextContent build() {
      return data;
    }
  }
}
