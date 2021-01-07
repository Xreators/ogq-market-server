package com.ogqcorp.market.hash.usecase.artwork.msg.output;

import com.ogqcorp.market.common.type.UniqueId;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:55 오후 Last Modified At: 2020/12/16
 */
public class ArtworkHashCreateOutMsg {
  private UniqueId artworkId;
  private String plainData;
  private String hashData;

  public ArtworkHashCreateOutMsg(UniqueId artworkId, String plainData, String hashData) {
    this.artworkId = artworkId;
    this.plainData = plainData;
    this.hashData = hashData;
  }

  public UniqueId getArtworkId() {
    return artworkId;
  }

  public String getPlainData() {
    return plainData;
  }

  public String getHashData() {
    return hashData;
  }
}
