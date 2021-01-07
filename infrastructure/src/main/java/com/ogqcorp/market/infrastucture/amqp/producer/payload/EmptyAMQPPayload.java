package com.ogqcorp.market.infrastucture.amqp.producer.payload;


import com.ogqcorp.market.common.amqp.msg.EmptyAMQPMsg;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 2:52 오후 Last Modified At: 2020/12/15
 */
public class EmptyAMQPPayload extends MarketAMQPPayload<EmptyAMQPMsg> {

  public EmptyAMQPPayload(String exchange, String routingKey) {
    super(exchange, routingKey, new EmptyAMQPMsg());
  }

}
