package com.ogqcorp.market.ontology.error.exception;

import com.ogqcorp.market.common.error.ErrorCodes.InfrastructureErrorCodes;
import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 12:23 오후 Last Modified At: 2020/12/21
 */
public class RpcCommException extends MarketException {

  public RpcCommException(String message) {
    super(InfrastructureErrorCodes.RPC_COMM_EXCEPTION, message);
  }

}
