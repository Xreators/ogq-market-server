package com.ogqcorp.market.repository.persistence.content.artwork;

import com.ogqcorp.market.domain.content.artwork.Artwork;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 3:22 오후 Last Modified At: 2020/12/09
 */
@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
  Optional<Artwork> findByArtworkId(ArtworkId artworkId);
}
