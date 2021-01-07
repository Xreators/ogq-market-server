package com.ogqcorp.market.domain.content.artwork;

import com.ogqcorp.market.common.util.TimeUtil;
import com.ogqcorp.market.domain.hash.ArtworkHashValue;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:32 오전 Last Modified At: 2020/12/21
 */

@Entity
@Table(name = "artwork_hash", indexes = {
    @Index(columnList = "artwork_uid"),
    @Index(columnList = "artwork_hash", unique = true),
})
public class ArtworkHash implements Comparable<ArtworkHash> {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "artwork_uid", nullable = false, length = 40))
  private ArtworkId artworkId;

  @AttributeOverride(name = "value", column = @Column(name = "artwork_hash", nullable = false, unique = true, length = 64))
  private ArtworkHashValue artworkHashValue;

  @Column(name="created_at")
  private ZonedDateTime createdAt;

  public ArtworkHash() {}

  public ArtworkHash(ArtworkId artworkId, ArtworkHashValue artworkHashValue) {
    this.artworkId = artworkId;
    this.artworkHashValue = artworkHashValue;
    this.createdAt = TimeUtil.utcNow();
  }

  public ArtworkId artworkId() {
    return artworkId;
  }
  public ArtworkHashValue artworkHash() { return artworkHashValue; }

  public ZonedDateTime createdAt() { return createdAt; }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkHash)) {
      return false;
    }
    ArtworkHash that = (ArtworkHash) o;
    return Objects.equals(artworkHashValue, that.artworkHashValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artworkHashValue);
  }

  @Override
  public int compareTo(ArtworkHash o) {
    return createdAt.compareTo(o.createdAt());
  }

  @Override
  public String toString() {
    return "ArtworkHash{" +
        "artworkId=" + artworkId +
        ", artworkHashValue=" + artworkHashValue +
        ", createdAt=" + createdAt +
        '}';
  }
}
