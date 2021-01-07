package com.ogqcorp.market.relay.interfaces.amqp.consumer;

import com.ogqcorp.market.common.adapter.FluxAdapterController;
import com.ogqcorp.market.common.amqp.msg.hash.ArtworkRelayRequestAMQPMsg;
import com.ogqcorp.market.relay.adapter.artwork.dto.req.ArtworkHashRelayReqDTO;
import com.ogqcorp.market.relay.adapter.artwork.dto.res.ArtworkHashRelayResDTO;

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
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:15 오후 Last Modified At: 2020/12/18
 */
@Service
public class ArtworkHashRelayMsgConsumer {
  private final Logger logger = LoggerFactory.getLogger(ArtworkHashRelayMsgConsumer.class);

  private final FluxAdapterController<ArtworkHashRelayReqDTO, ArtworkHashRelayResDTO> adapterController;

  @Autowired
  public ArtworkHashRelayMsgConsumer(
    final FluxAdapterController<ArtworkHashRelayReqDTO, ArtworkHashRelayResDTO> adapterController
  ) {
    this.adapterController = adapterController;
  }

  @RabbitListener(bindings = {
    @QueueBinding(
      exchange = @Exchange(type = ExchangeTypes.TOPIC, value = "${message.relay.topic}", durable = "true"),
      key = "${message.relay.routingKey.asset.relayHashReq}",
      value = @Queue(value = "${message.relay.queue.asset.relayHashReq}", durable = "true", exclusive = "false", autoDelete = "false")
    )
  }, containerFactory = "marketQueueFactory")
  public void artworkHashRelayMsgConsume(ArtworkRelayRequestAMQPMsg msg) {
    logger.info("received: {}", msg);

    adapterController.execute(new ArtworkHashRelayReqDTO(msg.getArtworkHashDataSeqIds()))
      .subscribe(
        (res) -> logger.info("{}: {}", res.getArtworkId(), res.getArtworkHashValue()),
        (err) -> logger.error("{}", err),
        (   ) -> logger.info("complete artwork relay")
      );
  }

}
