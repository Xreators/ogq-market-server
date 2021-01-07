package com.ogqcorp.market.common.amqp.msg.hash;

import com.ogqcorp.market.common.amqp.msg.MarketAMQPMsg;
import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:16 오후 Last Modified At: 2020/12/18
 */
public class ArtworkRelayRequestAMQPMsg implements MarketAMQPMsg {
  private List<Long> artworkHashDataSeqIds;

  private ArtworkRelayRequestAMQPMsg() {}
  public ArtworkRelayRequestAMQPMsg(List<Long> artworkHashDataSeqIds) {
    this.artworkHashDataSeqIds = artworkHashDataSeqIds;
  }

  public List<Long> getArtworkHashDataSeqIds() {
    return artworkHashDataSeqIds;
  }

  @Override
  public String toString() {
    return "ArtworkRelayRequestAMQPMsg{" +
        "artworkHashDataSeqIds=" + artworkHashDataSeqIds +
        '}';
  }
}
