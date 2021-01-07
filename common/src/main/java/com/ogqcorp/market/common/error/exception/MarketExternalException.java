package com.ogqcorp.market.common.error.exception;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 3:49 오후 Last Modified At: 2020/12/07
 */
public class MarketExternalException extends MarketException {

  public MarketExternalException(int code, String message) {
    super(code, message);
  }
}
