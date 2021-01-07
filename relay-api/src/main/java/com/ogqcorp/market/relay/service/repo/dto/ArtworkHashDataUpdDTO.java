package com.ogqcorp.market.relay.service.repo.dto;

import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/28 12:07 오후 Last Modified At: 2020/12/28
 */
public class ArtworkHashDataUpdDTO {
  private String artworkHash;
  private ArtworkHashDataStatus status;

  public ArtworkHashDataUpdDTO(
    String artworkHash,
    ArtworkHashDataStatus status
  ) {
    this.artworkHash = artworkHash;
    this.status = status;
  }

  public String getArtworkHash() {
    return artworkHash;
  }

  public ArtworkHashDataStatus getStatus() {
    return status;
  }
}
