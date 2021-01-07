package com.ogqcorp.market.repository.persistence.hash;

import com.ogqcorp.market.domain.hash.ArtworkRelayErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:17 오전 Last Modified At: 2020/12/21
 */
@Repository
public interface ArtworkRelayErrorLogRepository extends JpaRepository<ArtworkRelayErrorLog, Long> {

}
