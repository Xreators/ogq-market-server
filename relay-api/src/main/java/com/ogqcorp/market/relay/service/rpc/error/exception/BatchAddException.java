package com.ogqcorp.market.relay.service.rpc.error.exception;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 2:39 오후 Last Modified At: 2020/12/21
 */
public class BatchAddException extends MarketException {

  public BatchAddException(int code, String message) {
    super(code, message);
  }
}
