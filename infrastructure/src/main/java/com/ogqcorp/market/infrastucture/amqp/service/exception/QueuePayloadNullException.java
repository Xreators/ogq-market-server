package com.ogqcorp.market.infrastucture.amqp.service.exception;

import static com.ogqcorp.market.common.error.ErrorCodes.InfrastructureErrorCodes.QUEUE_PAYLOAD_IS_NULL_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 2:49 오후 Last Modified At: 2020/12/15
 */
public class QueuePayloadNullException extends MarketException {

  public QueuePayloadNullException() {
    super(QUEUE_PAYLOAD_IS_NULL_EXCEPTION, "payload is null exception");
  }
}
