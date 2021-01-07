package com.ogqcorp.market.relay.service.amqp.producer.payload;

import com.ogqcorp.market.common.amqp.msg.hash.ArtworkRelayResultAMQPMsg;
import com.ogqcorp.market.infrastucture.amqp.producer.payload.MarketAMQPPayload;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 3:43 오후 Last Modified At: 2020/12/21
 */
public class ArtworkRelayResultPayload extends MarketAMQPPayload<ArtworkRelayResultAMQPMsg> {

  public ArtworkRelayResultPayload(String exchange, String routingKey,
      ArtworkRelayResultAMQPMsg data) {
    super(exchange, routingKey, data);
  }

}
