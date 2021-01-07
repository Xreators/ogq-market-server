package com.ogqcorp.market.domain.content.uci;

import com.ogqcorp.market.common.util.TimeUtil;


import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.uci.UCICode;
import java.time.ZonedDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uciDocuments")
public class UCIDocument {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @AttributeOverride(name = "value", column = @Column(name = "artworkId", nullable = false, unique = true, length = 40))
  private ArtworkId artworkId;

  @AttributeOverride(name = "value", column = @Column(name = "uciCode", nullable = false, unique = true, length = 80))
  private UCICode uciCode;

  @AttributeOverride(name = "value", column = @Column(name = "creatorId", nullable = false, unique = true, length = 40))
  private CreatorId creatorId;

  @Enumerated(EnumType.STRING)
  @Column(length = 40)
  private UCIRequestType requestType;


  private ZonedDateTime createdAt;

  private ZonedDateTime updatedAt;

  public UCIDocument(){}

  public UCIDocument(ArtworkId artworkId, CreatorId creatorId, UCICode uciCode){
    this.artworkId = artworkId;
    this.uciCode = uciCode;
    this.creatorId = creatorId;
    this.requestType = UCIRequestType.CREATE;
    this.createdAt = TimeUtil.utcNow();
    this.updatedAt = TimeUtil.utcNow();

  }

  public void update(){
    this.updatedAt = TimeUtil.utcNow();
  }
  public UCICode uciCode(){
    return this.uciCode;
  }

  public ArtworkId getArtworkId() {
    return artworkId;
  }
}
