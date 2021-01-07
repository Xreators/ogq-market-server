package com.ogqcorp.market.common.type;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 4:24 오후 Last Modified At: 2020/12/09
 */
public class ImageSize {
  private int width;
  private int height;

  private ImageSize() {}
  public static Builder builder() {
    return new Builder();
  }
  public int getWidth() {
    return width;
  }
  public int getHeight() {
    return height;
  }

  public static class Builder {
    private ImageSize data;
    private Builder() {
      data = new ImageSize();
    }
    public Builder width(int width) {
      data.width = width;
      return this;
    }
    public Builder height(int height) {
      data.height = height;
      return this;
    }
    public ImageSize build() {
      return data;
    }
  }

}
