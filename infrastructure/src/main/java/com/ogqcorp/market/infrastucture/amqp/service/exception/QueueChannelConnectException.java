package com.ogqcorp.market.infrastucture.amqp.service.exception;

import static com.ogqcorp.market.common.error.ErrorCodes.InfrastructureErrorCodes.QUEUE_CHANNEL_CONNECT_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 11:12 오전 Last Modified At: 2020/12/15
 */
public class QueueChannelConnectException extends MarketException {

  public QueueChannelConnectException(String que) {
    super(QUEUE_CHANNEL_CONNECT_EXCEPTION, new StringBuilder("channel connect exception: [").append(que).append("]").toString());
  }
}
