package com.ogqcorp.market.relay.adapter.artwork.dto.req;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:44 오후 Last Modified At: 2020/12/18
 */
public class ArtworkHashRelayReqDTO {
  private List<Long> artworkHashDataSeqIds;

  public ArtworkHashRelayReqDTO(List<Long> artworkHashDataSeqIds) {
    this.artworkHashDataSeqIds = artworkHashDataSeqIds;
  }

  public List<Long> getArtworkHashDataSeqIds() {
    return artworkHashDataSeqIds;
  }
}
