package com.ogqcorp.market.hash.usecase.artwork.msg.intput;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:50 오후 Last Modified At: 2020/12/16
 */
public class ArtworkHashCreateInMsg {
  private long artworkSeqId;

  public ArtworkHashCreateInMsg(long artworkSeqId) {
    this.artworkSeqId = artworkSeqId;
  }

  public long getArtworkSeqId() {
    return artworkSeqId;
  }
}
