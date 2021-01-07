package com.ogqcorp.market.relay.usecase.artwork.msg.input;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:37 오후 Last Modified At: 2020/12/18
 */
public class ArtworkRelayInMsg {
  private List<Long> artworkHashDataSeqIds;

  public ArtworkRelayInMsg(List<Long> artworkHashDataSeqIds) {
    this.artworkHashDataSeqIds = artworkHashDataSeqIds;
  }

  public List<Long> getArtworkHashDataSeqIds() {
    return artworkHashDataSeqIds;
  }
}
