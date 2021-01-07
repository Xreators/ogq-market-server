package com.ogqcorp.market.domain.hash;

import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;
import com.ogqcorp.market.common.util.TimeUtil;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.uci.UCICode;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 3:33 오후 Last Modified At: 2020/12/16
 */

@Entity
@Table(name = "artwork_hash_data", indexes = {
    @Index(columnList = "artwork_hash", unique = true),
})
public class ArtworkHashData {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "creator_uid", nullable = false, length = 40))
  private CreatorId creatorId;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "artwork_uid", nullable = false, length = 40))
  private ArtworkId artworkId;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "uci_code", nullable = false, length = 80))
  private UCICode uciCode;

  // max length = 65535
  @Column(name="title", columnDefinition = "text", nullable = false)
  private String title;

  @Column(name="file_hash", nullable = false, length = 64)
  private String fileHash;

  @Column(name="data_inserted_at", nullable = false)
  private ZonedDateTime dataInsertedAt;

  @Column(name="json_data", columnDefinition = "text")
  private String jsonData;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "artwork_hash", nullable = false, unique = true, length = 64))
  private ArtworkHashValue artworkHashValue;

  @Column(name="artwork_hash_created_at")
  private ZonedDateTime artworkHashCreatedAt;

  @Column(name="data_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ArtworkHashDataStatus dataStatus;

  @Column(name="updated_at")
  private ZonedDateTime updatedAt;

  public ArtworkHashData() {}

  public ArtworkHashData(
    CreatorId creatorId,
    ArtworkId artworkId, UCICode uciCode, String title, String fileHash,
    long dataInsertedAt, String jsonData,
    ArtworkHashValue artworkHashValue
  ) {
    this.creatorId = creatorId;
    this.artworkId = artworkId;
    this.uciCode = uciCode;
    this.title = title;
    this.fileHash = fileHash;
    this.dataInsertedAt = TimeUtil.fromEpochSecond(dataInsertedAt);
    this.jsonData = jsonData;
    this.artworkHashValue = artworkHashValue;
    this.artworkHashCreatedAt = TimeUtil.utcNow();
    this.updatedAt = this.artworkHashCreatedAt;
    this.dataStatus = ArtworkHashDataStatus.CREATED;
  }

  public long getId() { return id; }

  public ArtworkId artworkId() {
    return artworkId;
  }

  public String jsonData() {
    return jsonData;
  }

  public ArtworkHashValue artworkHashValue() {
    return artworkHashValue;
  }

  public ArtworkHashDataStatus dataStatus() {
    return dataStatus;
  }
  public void updateStatus(ArtworkHashDataStatus dataStatus) {
    this.dataStatus = dataStatus;
  }
  public void request() {
    dataStatus = ArtworkHashDataStatus.REQUEST;
  }
  public void success() {
    dataStatus = ArtworkHashDataStatus.SUCCESS;
  }
  public void fail() {
    dataStatus = ArtworkHashDataStatus.FAIL;
  }

  public ZonedDateTime getArtworkHashCreatedAt() {
    return artworkHashCreatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtworkHashData)) {
      return false;
    }
    ArtworkHashData that = (ArtworkHashData) o;
    return Objects.equals(creatorId, that.creatorId) && Objects
        .equals(artworkId, that.artworkId) && Objects.equals(uciCode, that.uciCode)
        && Objects.equals(title, that.title) && Objects
        .equals(fileHash, that.fileHash) && Objects
        .equals(dataInsertedAt, that.dataInsertedAt) && Objects
        .equals(jsonData, that.jsonData) && Objects
        .equals(artworkHashValue, that.artworkHashValue) && Objects
        .equals(artworkHashCreatedAt, that.artworkHashCreatedAt) && dataStatus == that.dataStatus
        && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creatorId, artworkId, uciCode, title, fileHash, dataInsertedAt, jsonData,
        artworkHashValue, artworkHashCreatedAt, dataStatus, updatedAt);
  }
}
