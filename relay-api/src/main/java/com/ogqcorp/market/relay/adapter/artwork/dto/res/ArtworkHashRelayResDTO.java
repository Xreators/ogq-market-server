package com.ogqcorp.market.relay.adapter.artwork.dto.res;

import com.ogqcorp.market.common.type.UniqueId;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:44 오후 Last Modified At: 2020/12/18
 */
public class ArtworkHashRelayResDTO {
  private final UniqueId artworkId;
  private final String artworkHashValue;

  public ArtworkHashRelayResDTO(final UniqueId artworkId, final String artworkHashValue) {
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
