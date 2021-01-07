package com.ogqcorp.market.common.error.exception;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 3:48 오후 Last Modified At: 2020/12/07
 */
public class MarketInternalException extends MarketException {

  public MarketInternalException(int code, String message) {
    super(code, message);
  }
}
