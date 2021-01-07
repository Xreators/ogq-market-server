package com.ogqcorp.market.infrastucture.amqp.producer.payload;

import com.ogqcorp.market.common.amqp.msg.MarketAMQPMsg;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 2:15 오후 Last Modified At: 2020/12/15
 */
public abstract class MarketAMQPPayload<T extends MarketAMQPMsg> {
  private String exchange;
  private String routingKey;
  private T data;

  protected MarketAMQPPayload(String exchange, String routingKey, T data) {
    this.exchange = exchange;
    this.routingKey = routingKey;
    this.data = data;
  }

  public String getExchange() {
    return exchange;
  }

  public String getRoutingKey() {
    return routingKey;
  }

  public T getData() {
    return data;
  }
}
