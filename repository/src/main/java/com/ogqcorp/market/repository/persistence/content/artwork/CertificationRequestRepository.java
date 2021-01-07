package com.ogqcorp.market.repository.persistence.content.artwork;

import com.ogqcorp.market.domain.content.artwork.Artwork;
import com.ogqcorp.market.domain.content.certification.CertificationCreateRequest;
import com.ogqcorp.market.domain.content.certification.CertificationCreateRequestStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRequestRepository extends
    JpaRepository<CertificationCreateRequest, Long> {

  Optional<CertificationCreateRequest> findByArtwork(Artwork artwork);

  @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "artwork")
  List<CertificationCreateRequest> findTop100ByCreateRequestStatusOrderByRequestedAtAsc(CertificationCreateRequestStatus status);
}
