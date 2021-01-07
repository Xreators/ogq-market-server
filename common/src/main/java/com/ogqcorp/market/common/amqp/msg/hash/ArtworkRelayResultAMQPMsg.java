package com.ogqcorp.market.common.amqp.msg.hash;

import com.ogqcorp.market.common.amqp.msg.MarketAMQPMsg;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 3:38 오후 Last Modified At: 2020/12/21
 */
public class ArtworkRelayResultAMQPMsg implements MarketAMQPMsg {
  private String artworkId;
  private String artworkHashValue;

  private ArtworkRelayResultAMQPMsg() {}
  public ArtworkRelayResultAMQPMsg(String artworkId, String artworkHashValue) {
    this.artworkId = artworkId;
    this.artworkHashValue = artworkHashValue;
  }

  public String getArtworkId() {
    return artworkId;
  }

  public String getArtworkHashValue() {
    return artworkHashValue;
  }

  @Override
  public String toString() {
    return "ArtworkRelayResultAMQPMsg{" +
        "artworkId='" + artworkId + '\'' +
        ", artworkHashValue='" + artworkHashValue + '\'' +
        '}';
  }
}
