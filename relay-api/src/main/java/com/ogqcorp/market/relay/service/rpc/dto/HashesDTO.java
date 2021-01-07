package com.ogqcorp.market.relay.service.rpc.dto;

import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 2:31 오후 Last Modified At: 2020/12/21
 */
public class HashesDTO {
  private final List<String> hashes;

  public HashesDTO(List<String> hashes) {
    this.hashes = hashes;
  }

  public List<String> getHashes() {
    return hashes;
  }
}
