package com.ogqcorp.market.domain.hash;

import com.ogqcorp.market.common.type.task.RelayStatus;
import com.ogqcorp.market.common.util.TimeUtil;

import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "artworkRelayRequest", indexes = {
    @Index(columnList = "requestStatus"),
    @Index(columnList = "id, requestStatus"),
})
public class ArtworkRelayRequest {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Column(name="artworkHashData_id")
  private long artworkHashData_id;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private RelayStatus requestStatus;

  private ZonedDateTime createdAt;

  private ZonedDateTime updatedAt;

  public ArtworkRelayRequest() {}
  public ArtworkRelayRequest(long lastReqId) {
    this.artworkHashData_id = lastReqId;
    this.requestStatus = RelayStatus.WAIT;
    this.createdAt = TimeUtil.utcNow();
    this.updatedAt = this.createdAt;
  }

  public long getArtworkHashData_id() {
    return artworkHashData_id;
  }

  public void done() {
    this.requestStatus = RelayStatus.DONE;
    this.updatedAt = TimeUtil.utcNow();
  }

  public RelayStatus getRequestStatus() {
    return requestStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkRelayRequest)) {
      return false;
    }
    ArtworkRelayRequest that = (ArtworkRelayRequest) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
