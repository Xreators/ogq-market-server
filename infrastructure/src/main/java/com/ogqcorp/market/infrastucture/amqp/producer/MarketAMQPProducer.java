package com.ogqcorp.market.infrastucture.amqp.producer;

import com.ogqcorp.market.common.amqp.msg.MarketAMQPMsg;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 2:14 오후 Last Modified At: 2020/12/15
 */
@FunctionalInterface
public interface MarketAMQPProducer<T extends MarketAMQPMsg, V> {

  Mono<V> produce(T t);

}
