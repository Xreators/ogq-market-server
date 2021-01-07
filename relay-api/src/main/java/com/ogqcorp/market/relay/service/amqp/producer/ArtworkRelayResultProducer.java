package com.ogqcorp.market.relay.service.amqp.producer;

import com.ogqcorp.market.common.amqp.msg.hash.ArtworkRelayResultAMQPMsg;
import com.ogqcorp.market.infrastucture.amqp.producer.MarketAMQPProducer;
import com.ogqcorp.market.infrastucture.amqp.service.QueueProduceService;
import com.ogqcorp.market.relay.service.amqp.producer.payload.ArtworkRelayResultPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 3:34 오후 Last Modified At: 2020/12/21
 */
@Service
public class ArtworkRelayResultProducer implements MarketAMQPProducer<ArtworkRelayResultAMQPMsg, Void> {
  private final QueueProduceService queueProduceService;
  private final String contentExchange;
  private final String certGenRoutingKey;

  @Autowired
  public ArtworkRelayResultProducer(
    final QueueProduceService queueProduceService,
    final @Value("${message.content.topic}") String contentExchange,
    final @Value("${message.content.routingKey.certification.next-code}") String certGenRoutingKey
  ) {
    this.queueProduceService = queueProduceService;
    this.contentExchange = contentExchange;
    this.certGenRoutingKey = certGenRoutingKey;
  }

  @Override
  public Mono<Void> produce(ArtworkRelayResultAMQPMsg inMsg) {
    return Mono.fromCallable(() -> inMsg)
      .publishOn(Schedulers.boundedElastic())
      .map(it -> {
        queueProduceService.produce(new ArtworkRelayResultPayload(contentExchange, certGenRoutingKey, it));
        return it;
      })
      .then()
      .onErrorResume(Mono::error)
      ;
  }
}
