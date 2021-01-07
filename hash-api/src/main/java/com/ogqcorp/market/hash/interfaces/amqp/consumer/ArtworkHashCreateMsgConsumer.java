package com.ogqcorp.market.hash.interfaces.amqp.consumer;

import com.ogqcorp.market.common.amqp.msg.hash.ArtworkHashCreateAMQPMsg;
import com.ogqcorp.market.hash.adapter.artwork.controller.ArtworkHashCreateController;
import com.ogqcorp.market.hash.adapter.artwork.dto.req.ArtworkHashCreateReqDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 1:46 오후 Last Modified At: 2020/12/16
 */
@Service
public class ArtworkHashCreateMsgConsumer {
  private final Logger logger = LoggerFactory.getLogger(ArtworkHashCreateMsgConsumer.class);

  private ArtworkHashCreateController artworkHashCreateController;

  @Autowired
  public ArtworkHashCreateMsgConsumer(
      ArtworkHashCreateController artworkHashCreateController) {
    this.artworkHashCreateController = artworkHashCreateController;
  }

  @RabbitListener(bindings = {
    @QueueBinding(
      exchange = @Exchange(type = ExchangeTypes.TOPIC, value = "${message.relay.topic}", durable = "true"),
      key = "${message.relay.routingKey.asset.createHashReq}",
      value = @Queue(value = "${message.relay.queue.asset.createHashReq}", durable = "true", exclusive = "false", autoDelete = "false")
    )
  }, containerFactory = "marketQueueFactory")
  public void artworkHashCreateMsgConsume(ArtworkHashCreateAMQPMsg msg) {
    logger.info("received: {}", msg);

    artworkHashCreateController.execute(new ArtworkHashCreateReqDTO(msg.getArtworkSeqId(), msg.getArtworkType()))
      .subscribe(
        (res) -> logger.info("created success: {}", res),
        (err) -> logger.error("created fail ", err),
        (   ) -> logger.info("finish consume(id: {})", msg.getArtworkSeqId())
      );
  }

}
