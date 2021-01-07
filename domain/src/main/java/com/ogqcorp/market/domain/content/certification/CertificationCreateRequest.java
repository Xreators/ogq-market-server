package com.ogqcorp.market.domain.content.certification;

import com.ogqcorp.market.common.type.artwork.ArtworkType;
import com.ogqcorp.market.common.util.TimeUtil;
import com.ogqcorp.market.domain.content.artwork.Artwork;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "certificationCreateRequest",
    indexes = {
        @Index(columnList = "artwork_id", unique = true),
        @Index(columnList = "createRequestStatus, requestedAt"),
    }
)
public class CertificationCreateRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "artwork_id", referencedColumnName = "id")
  private Artwork artwork;

  @Enumerated(EnumType.STRING)
  @Column(length = 40)
  private CertificationCreateRequestStatus createRequestStatus;

  private ZonedDateTime requestedAt;
  private ZonedDateTime updatedAt;


  public CertificationCreateRequest(Artwork artwork) {
    this.artwork = artwork;
    this.createRequestStatus = CertificationCreateRequestStatus.STARTED;
    this.requestedAt = TimeUtil.utcNow();
    this.updatedAt = TimeUtil.utcNow();
  }
  public CertificationCreateRequest() {}

  public Artwork artwork() { return this.artwork; }
  public ArtworkType artworkType() { return this.artwork.artworkType(); }

  public CertificationCreateRequestStatus requestStatus() {
    return createRequestStatus;
  }
  public Long seqId() { return id; }

  public void request() { updateStatus(CertificationCreateRequestStatus.REQUESTED); }
  public void waited() { updateStatus(CertificationCreateRequestStatus.WAITED); }
  public void complete() { updateStatus(CertificationCreateRequestStatus.COMPLETED); }
  public void fail() { updateStatus(CertificationCreateRequestStatus.FAILED); }

  private void updateStatus(CertificationCreateRequestStatus status) {
    this.createRequestStatus = status;
    this.updatedAt = TimeUtil.utcNow();
  }
}

