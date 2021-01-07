package com.ogqcorp.market.relay.service.repo.dto;

import com.ogqcorp.market.common.type.UniqueId;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:43 오전 Last Modified At: 2020/12/21
 */
public class ArtworkHashInsDTO {
  private UniqueId uniqueId;
  private String hash;

  public ArtworkHashInsDTO(UniqueId uniqueId, String hash) {
    this.uniqueId = uniqueId;
    this.hash = hash;
  }

  public UniqueId getUniqueId() {
    return uniqueId;
  }

  public String getHash() {
    return hash;
  }
}
