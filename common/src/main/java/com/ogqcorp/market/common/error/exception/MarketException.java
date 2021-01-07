package com.ogqcorp.market.common.error.exception;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 3:37 오후 Last Modified At: 2020/12/07
 */
public abstract class MarketException extends RuntimeException {
  private final int code;

  public MarketException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
  }
