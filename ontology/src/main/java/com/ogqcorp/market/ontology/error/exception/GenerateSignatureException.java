package com.ogqcorp.market.ontology.error.exception;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/12 11:53 오전 Last Modified At: 2020/05/12
 */
public class GenerateSignatureException extends MarketException {

  public GenerateSignatureException(int code, String message) {
    super(code, message);
  }
}
