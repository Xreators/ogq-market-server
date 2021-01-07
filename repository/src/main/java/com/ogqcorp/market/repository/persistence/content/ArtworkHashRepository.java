package com.ogqcorp.market.repository.persistence.content;

import com.ogqcorp.market.domain.content.artwork.ArtworkHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:37 오전 Last Modified At: 2020/12/21
 */
@Repository
public interface ArtworkHashRepository extends JpaRepository<ArtworkHash, Long> {

}
