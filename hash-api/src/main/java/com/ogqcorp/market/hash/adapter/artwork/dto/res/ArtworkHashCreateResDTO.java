package com.ogqcorp.market.hash.adapter.artwork.dto.res;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:26 오후 Last Modified At: 2020/12/16
 */
public class ArtworkHashCreateResDTO {
  private String artworkId;
  private String plainData;
  private String hashData;

  private ArtworkHashCreateResDTO() {}
  public static Builder builder() {
    return new Builder();
  }

  public String getArtworkId() {
    return artworkId;
  }

  public String getPlainData() {
    return plainData;
  }

  public String getHashData() {
    return hashData;
  }

  @Override
  public String toString() {
    return "ArtworkHashCreateResDTO{" +
        "artworkId='" + artworkId + '\'' +
        ", plainData='" + plainData + '\'' +
        ", hashData='" + hashData + '\'' +
        '}';
  }

  public static class Builder {
    private ArtworkHashCreateResDTO data;

    public Builder() {
      data = new ArtworkHashCreateResDTO();
    }
    public Builder artworkId(String artworkId) {
      data.artworkId = artworkId;
      return this;
    }
    public Builder plainData(String plainData) {
      data.plainData = plainData;
      return this;
    }
    public Builder hashData(String hashData) {
      data.hashData = hashData;
      return this;
    }
    public ArtworkHashCreateResDTO build() {
      return data;
    }
  }

}
