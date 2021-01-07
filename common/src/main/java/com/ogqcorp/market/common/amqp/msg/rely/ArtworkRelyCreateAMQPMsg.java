package com.ogqcorp.market.common.amqp.msg.rely;

import com.ogqcorp.market.common.amqp.msg.MarketAMQPMsg;
import com.ogqcorp.market.common.type.artwork.ArtworkType;
import java.util.List;
import java.util.Objects;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 2:22 오후 Last Modified At: 2020/12/16
 */
public class ArtworkRelyCreateAMQPMsg implements MarketAMQPMsg {
  private List<Long> artworkHashDataSeqIds;

  private ArtworkRelyCreateAMQPMsg() {}
  public static Builder builder() {
    return new Builder();
  }

  public List<Long> getArtworkHashDataSeqIds() {
    return artworkHashDataSeqIds;
  }

  public static class Builder {
    private ArtworkRelyCreateAMQPMsg data;

    private Builder() {
      data = new ArtworkRelyCreateAMQPMsg();
    }
    public Builder artworkHashDataSeqIds(List<Long> artworkHashDataSeqIds) {
      data.artworkHashDataSeqIds = artworkHashDataSeqIds;
      return this;
    }

    public ArtworkRelyCreateAMQPMsg build() {
      return data;
    }
  }
}
