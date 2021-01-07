package com.ogqcorp.market.common.rpc.uci.response;

import com.ogqcorp.market.common.type.UniqueId;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 4:30 오후 Last Modified At: 2020/12/18
 */
public class UCIDocCreateRes {
  private String artworkId;
  private String uciCode;

  public UCIDocCreateRes(UniqueId artworkId, String uciCode) {
    this.artworkId = artworkId.idString();
    this.uciCode = uciCode;
  }

  public String getArtworkId() {
    return artworkId;
  }

  public String getUciCode() {
    return uciCode;
  }
}
