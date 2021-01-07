package com.ogqcorp.market.relay.usecase.artwork.msg.output;

import com.ogqcorp.market.common.type.UniqueId;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:39 오후 Last Modified At: 2020/12/18
 */
public class ArtworkRelayOutMsg {
  private final UniqueId artworkId;
  private final String artworkHashValue;

  public ArtworkRelayOutMsg(final UniqueId artworkId, final String artworkHashValue) {
    this.artworkId = artworkId;
    this.artworkHashValue = artworkHashValue;
  }

  public UniqueId getArtworkId() {
    return artworkId;
  }

  public String getArtworkHashValue() {
    return artworkHashValue;
  }

}
