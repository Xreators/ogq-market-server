package com.ogqcorp.market.infrastucture.amqp.service;

import com.ogqcorp.market.infrastucture.amqp.service.exception.QueueChannelConnectException;
import com.rabbitmq.client.Channel;
import java.io.IOException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/03 3:50 오후 Last Modified At: 2020/07/03
 */
public class QueueInfoService {

  private final Channel channel;
  private int artworkNumOfCreateQueueMsgThreshold;
  private int artworkNumOfRelayQueueMsgThreshold;
  private int creatorNumOfCreateQueueMsgThreshold;
  private int creatorNumOfRelayQueueMsgThreshold;
  private String assetHashCreateReq;
  private  String assetHashRelayReq;
  private String creatorHashCreateReq;
  private String creatorHashRelayReq;

  public QueueInfoService(
      Channel channel,
      int artworkNumOfCreateQueueMsgThreshold,
      int artworkNumOfRelayQueueMsgThreshold,
      int creatorNumOfCreateQueueMsgThreshold,
      int creatorNumOfRelayQueueMsgThreshold,
      String assetHashCreateReq,
      String assetHashRelayReq,
      String creatorHashCreateReq,
      String creatorHashRelayReq
  ) {
    this.channel = channel;
    this.artworkNumOfCreateQueueMsgThreshold = artworkNumOfCreateQueueMsgThreshold;
    this.artworkNumOfRelayQueueMsgThreshold = artworkNumOfRelayQueueMsgThreshold;
    this.creatorNumOfCreateQueueMsgThreshold = creatorNumOfCreateQueueMsgThreshold;
    this.creatorNumOfRelayQueueMsgThreshold = creatorNumOfRelayQueueMsgThreshold;
    this.assetHashCreateReq = assetHashCreateReq;
    this.assetHashRelayReq = assetHashRelayReq;
    this.creatorHashCreateReq = creatorHashCreateReq;
    this.creatorHashRelayReq = creatorHashRelayReq;
  }

  public boolean isAllowArtworkHashCreate() {
    try {
      return getChannelsMessageCount(assetHashCreateReq) < artworkNumOfCreateQueueMsgThreshold;
    } catch (IOException e) {
      e.printStackTrace();
      throw new QueueChannelConnectException(assetHashCreateReq);
    }
  }
  public boolean isAllowArtworkHashRelay() {
    try {
      return getChannelsMessageCount(assetHashRelayReq) < artworkNumOfRelayQueueMsgThreshold;
    } catch (IOException e) {
      e.printStackTrace();
      throw new QueueChannelConnectException(assetHashRelayReq);
    }
  }
  public boolean isAllowCreatorHashCreate() {
    try {
      return getChannelsMessageCount(creatorHashCreateReq) < creatorNumOfCreateQueueMsgThreshold;
    } catch (IOException e) {
      e.printStackTrace();
      throw new QueueChannelConnectException(creatorHashCreateReq);
    }
  }
  public boolean isAllowCreatorHashRelay() {
    try {
      return getChannelsMessageCount(creatorHashRelayReq) < creatorNumOfRelayQueueMsgThreshold;
    } catch (IOException e) {
      e.printStackTrace();
      throw new QueueChannelConnectException(creatorHashRelayReq);
    }
  }
  public long getChannelsMessageCount(String queue) throws IOException {
    return channel.messageCount(queue);
  }

}
