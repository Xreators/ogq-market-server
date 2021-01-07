package com.ogqcorp.market.hash.adapter.artwork.dto.req;

import com.ogqcorp.market.common.type.artwork.ArtworkType;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:26 오후 Last Modified At: 2020/12/16
 */
public class ArtworkHashCreateReqDTO {
  private long artworkSeqId;
  private ArtworkType artworkType;

  public ArtworkHashCreateReqDTO(long artworkSeqId,
      ArtworkType artworkType) {
    this.artworkSeqId = artworkSeqId;
    this.artworkType = artworkType;
  }

  public long getArtworkSeqId() {
    return artworkSeqId;
  }

  public ArtworkType getArtworkType() {
    return artworkType;
  }
}
