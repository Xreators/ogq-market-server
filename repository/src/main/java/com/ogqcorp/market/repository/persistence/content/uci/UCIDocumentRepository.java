package com.ogqcorp.market.repository.persistence.content.uci;

import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import java.util.Optional;
import com.ogqcorp.market.domain.content.uci.UCIDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UCIDocumentRepository extends JpaRepository<UCIDocument, Long> {
  Optional<UCIDocument> findByArtworkId(ArtworkId artworkId);
}
