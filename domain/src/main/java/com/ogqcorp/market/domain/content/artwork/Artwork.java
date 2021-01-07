package com.ogqcorp.market.domain.content.artwork;

import com.ogqcorp.market.common.type.Language;
import com.ogqcorp.market.common.type.artwork.ArtworkStatus;
import com.ogqcorp.market.common.type.artwork.ArtworkType;
import com.ogqcorp.market.common.type.artwork.Tag;
import com.ogqcorp.market.common.type.artwork.TextContent;
import com.ogqcorp.market.common.util.TimeUtil;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 3:23 오후 Last Modified At: 2020/12/09
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artworks",
  indexes = {
    @Index(columnList = "artwork_uid", unique = true),
  }
)
@DiscriminatorColumn(name = "artwork_type")
public abstract class Artwork {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "artwork_uid", unique = true, nullable = false, length = 40))
  private ArtworkId artworkId;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "creator_uid", nullable = false, length = 40))
  private CreatorId creatorId;

  @Column(name = "artwork_type", length = 31, nullable = false, insertable = false, updatable = false)
  @Enumerated(EnumType.STRING)
  private ArtworkType artworkType;

  @Column(name = "status", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private ArtworkStatus status;

  @Embedded
  @AttributeOverride(name = "value", column = @Column(name = "raw_tags", columnDefinition = "TEXT"))
  private ArtworkTag tags;

  @Column(name = "create_certification", nullable = false)
  private boolean createCertification;

  private ZonedDateTime registeredAt;
  private ZonedDateTime updatedAt;

  @ElementCollection
  @Fetch(FetchMode.SUBSELECT)
  @CollectionTable(name = "artwork_text_contents", joinColumns = @JoinColumn(name = "artwork_id", referencedColumnName = "id", insertable = false, updatable = false))
  @MapKeyJoinColumn(name = "language")
  @MapKeyColumn(name = "language", length = 10)
  @MapKeyEnumerated(EnumType.STRING)
  private Map<Language, ArtworkTextContent> artworkTextContents = new HashMap<>();

  protected Artwork() {}
  protected Artwork(
    ArtworkId artworkId, ArtworkType artworkType,
    CreatorId creatorId,
    List<Tag> tags, Map<Language, TextContent> textContents,
    boolean createCertification
  ) {
    this.artworkId = artworkId;
    this.creatorId = creatorId;
    this.artworkType = artworkType;
    this.status = ArtworkStatus.REGISTERED;
    this.tags = ArtworkTag.fromTagList(tags);
    this.artworkTextContents = Optional.ofNullable(textContents)
      .map(it -> it.entrySet().stream().collect(Collectors.toMap(
        Entry::getKey,
        e -> ArtworkTextContent.fromTextContent(e.getValue())
      ))).orElseGet(() -> new HashMap<>());
    this.createCertification = createCertification;
    this.registeredAt = TimeUtil.utcNow();
    this.updatedAt = this.registeredAt;
  }
  public long seqId() { return id; }
  public ArtworkId artworkId() {
    return artworkId;
  }
  public CreatorId creatorId() {
    return creatorId;
  }
  public ArtworkType artworkType() {
    return artworkType;
  }
  public ArtworkStatus artworkStatus() {
    return status;
  }
  public List<Tag> getTags() {
    return Optional.ofNullable(tags)
      .map(ArtworkTag::toTags)
      .orElseGet(() -> Collections.emptyList());
  }
  public ArtworkTextContent textContent(Language language) {
    return artworkTextContents.get(language);
  }
  public Map<Language, ArtworkTextContent> allTextContent() {
    return artworkTextContents;
  }
  public Map<Language, String> allTitles() {
    return artworkTextContents.entrySet().stream().collect(Collectors.toMap(
      Entry::getKey,
      e -> e.getValue().title()
    ));
  }
  public boolean createCertification() {
    return createCertification;
  }
  public ZonedDateTime registeredAt() {
    return registeredAt;
  }
  public ZonedDateTime updatedAt() {
    return updatedAt;
  }
}
