package com.ogqcorp.market.common.rpc.uci.response;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/22 10:56 오전 Last Modified At: 2020/12/22
 */
public class UCIDocGetRes {
  private String uciCode;

  public UCIDocGetRes(String uciCode) {
    this.uciCode = uciCode;
  }

  public String getUciCode() {
    return uciCode;
  }
}
