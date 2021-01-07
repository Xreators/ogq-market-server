package com.ogqcorp.market.repository.persistence.content.task;

import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.certification.Certification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

  Optional<Certification> findByArtworkId(ArtworkId artworkId);

}
