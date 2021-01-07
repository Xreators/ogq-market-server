package com.ogqcorp.market.common.amqp.msg.hash;

import com.ogqcorp.market.common.amqp.msg.MarketAMQPMsg;
import com.ogqcorp.market.common.type.artwork.ArtworkType;
import java.util.Objects;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 2:22 오후 Last Modified At: 2020/12/16
 */
public class ArtworkHashCreateAMQPMsg implements MarketAMQPMsg {
  private long artworkSeqId;
  private ArtworkType artworkType;

  private ArtworkHashCreateAMQPMsg() {}
  public static Builder builder() {
    return new Builder();
  }

  public long getArtworkSeqId() {
    return artworkSeqId;
  }

  public ArtworkType getArtworkType() {
    return artworkType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkHashCreateAMQPMsg)) {
      return false;
    }
    ArtworkHashCreateAMQPMsg that = (ArtworkHashCreateAMQPMsg) o;
    return artworkSeqId == that.artworkSeqId && artworkType == that.artworkType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(artworkSeqId, artworkType);
  }

  @Override
  public String toString() {
    return "ArtworkHashCreateAMQPMsg{" +
        "artworkSeqId=" + artworkSeqId +
        ", artworkType=" + artworkType +
        '}';
  }

  public static class Builder {
    private ArtworkHashCreateAMQPMsg data;

    private Builder() {
      data = new ArtworkHashCreateAMQPMsg();
    }
    public Builder artworkSeqId(long artworkSeqId) {
      data.artworkSeqId = artworkSeqId;
      return this;
    }
    public Builder artworkType(ArtworkType artworkType) {
      data.artworkType = artworkType;
      return this;
    }
    public ArtworkHashCreateAMQPMsg build() {
      return data;
    }
  }
}
