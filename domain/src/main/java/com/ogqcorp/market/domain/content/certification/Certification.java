package com.ogqcorp.market.domain.content.certification;

import com.ogqcorp.market.common.util.TimeUtil;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.domain.content.artwork.Artwork;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "certification",
    indexes = {
        @Index(columnList = "creator_uid, created_at"),
        @Index(columnList = "artwork_uid", unique = true),
        @Index(columnList = "code_prefix, created_year, next_code", unique = true),
    }
)
public class Certification {
  public static final String CODE_PREFIX = "OGQ";
  private static final String CODE_INFIX = "-";


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "creator_uid", length = 40))
  private CreatorId creatorId;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "artwork_uid", length = 40))
  private ArtworkId artworkId;

  @Column(name = "code_prefix", length = 10)
  private String codePrefix;
  @Column(name = "created_year")
  private int createdYear;

  @Column(name = "next_code", length = 100)
  private String nextCode;

  @Column(name = "num_of_hash")
  private int numOfHash;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  public Certification(){}
  public Certification(Artwork artwork, int createdYear) {
    this.creatorId = artwork.creatorId();
    this.artworkId = artwork.artworkId();
    this.codePrefix = CODE_PREFIX;
    this.createdYear = createdYear;
    this.numOfHash = 1;
    this.createdAt = TimeUtil.utcNow();
    this.updatedAt = createdAt;
    this.nextCode = UUID.randomUUID().toString();
  }
  public long sequence() {
    return id;
  }
  public CreatorId creatorId() {
    return creatorId;
  }
  public ArtworkId artworkId() {
    return artworkId;
  }

  public int numOfHash() {
    return numOfHash;
  }
  public long createdAt() {
    return createdAt.toEpochSecond();
  }
  public long updatedAt() {
    return updatedAt.toEpochSecond();
  }

  public String nextCode() {
    StringBuilder sb = new StringBuilder(codePrefix);
    sb.append(CODE_INFIX);
    sb.append(createdYear);
    sb.append(CODE_INFIX);
    sb.append(nextCode);
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Certification)) {
      return false;
    }
    Certification c2 = (Certification) obj;
    return this.nextCode().equals(c2.nextCode());
  }

  public void plusNumOfHash() {
    this.numOfHash += 1;
    this.updatedAt = TimeUtil.utcNow();
  }

}