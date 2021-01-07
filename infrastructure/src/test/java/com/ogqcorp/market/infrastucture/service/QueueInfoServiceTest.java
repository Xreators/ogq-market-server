package com.ogqcorp.market.infrastucture.service;


import com.ogqcorp.market.infrastucture.amqp.producer.payload.EmptyAMQPPayload;
import com.ogqcorp.market.infrastucture.amqp.service.QueueInfoService;
import com.ogqcorp.market.infrastucture.amqp.service.QueueProduceService;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/15 11:20 오전 Last Modified At: 2020/12/15
 */
@Profile(value = {"dev","test"})
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestConstructor(autowireMode = AutowireMode.ALL)
@SpringBootTest
//@Disabled("QueueInfoService Test 비활성화")
public class QueueInfoServiceTest {
  private Logger logger = LoggerFactory.getLogger(QueueInfoServiceTest.class);


  private QueueInfoService queueInfoService;

  private QueueProduceService queueProduceService;
  private String ocsHashRelayExchange;
  private String assetHashCreateReq;
  private String assetHashRelayReq;
  private String creatorHashCreateReq;
  private String creatorHashRelayReq;

  public QueueInfoServiceTest(
      QueueInfoService queueInfoService,
      QueueProduceService queueProduceService,
      @Value("${message.relay.topic}") String ocsHashRelayExchange,
      @Value("${message.relay.routingKey.asset.createHashReq}") String assetHashCreateReq,
      @Value("${message.relay.routingKey.asset.relayHashReq}") String assetHashRelayReq,
      @Value("${message.relay.routingKey.creator.createHashReq}") String creatorHashCreateReq,
      @Value("${message.relay.routingKey.creator.relayHashReq}") String creatorHashRelayReq
  ) {
    this.queueInfoService = queueInfoService;
    this.queueProduceService = queueProduceService;
    this.ocsHashRelayExchange = ocsHashRelayExchange;
    this.assetHashCreateReq = assetHashCreateReq;
    this.assetHashRelayReq = assetHashRelayReq;
    this.creatorHashCreateReq = creatorHashCreateReq;
    this.creatorHashRelayReq = creatorHashRelayReq;
  }

  @Test
  public void test__GET_CHANNEL_MSG_COUNT() throws IOException {
    long before = queueInfoService.getChannelsMessageCount(assetHashCreateReq);
    queueProduceService.produce(new EmptyAMQPPayload(ocsHashRelayExchange, assetHashCreateReq));
    long expected = before + 1;
    logger.debug("before: {}", before);
    logger.debug("expected: {}", expected);
    Assertions.assertEquals(expected, queueInfoService.getChannelsMessageCount(assetHashCreateReq));
  }
}
