package com.ogqcorp.market.infrastucture.amqp.service;

import com.ogqcorp.market.infrastucture.amqp.service.exception.QueuePayloadNullException;
import com.ogqcorp.market.infrastucture.amqp.producer.payload.MarketAMQPPayload;
import java.util.Optional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 11:28 오전 Last Modified At: 2020/12/15
 */
@Service
public class QueueProduceService {

  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public QueueProduceService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void produce(MarketAMQPPayload payload) {
    Optional.ofNullable(payload)
      .ifPresentOrElse(
        this::execute,
        () -> new QueuePayloadNullException()
      );
  }
  private void execute(MarketAMQPPayload it) {
    this.rabbitTemplate.convertAndSend(it.getExchange(), it.getRoutingKey(), it.getData());
  }

}
