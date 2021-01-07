package com.ogqcorp.market.ontology.error.exception;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/12 2:44 오후 Last Modified At: 2020/05/12
 */
public class RpcInitException extends MarketException {

  public RpcInitException(int code, String message) {
    super(code, message);
  }
}
