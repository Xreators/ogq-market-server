package com.ogqcorp.market.common.rpc.uci.request;

public class UCIDocCreateReq {
  private String artworkId;

  public UCIDocCreateReq() {}  // for feign client
  public UCIDocCreateReq(String artworkId) {
    this.artworkId = artworkId;
  }

  public String getArtworkId() {
    return artworkId;
  }

}
