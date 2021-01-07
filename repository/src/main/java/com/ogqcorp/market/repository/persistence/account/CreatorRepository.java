package com.ogqcorp.market.repository.persistence.account;

import com.ogqcorp.market.domain.account.creator.Creator;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 2:46 오후 Last Modified At: 2020/12/09
 */
@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long> {
  Optional<Creator> findByCreatorId(CreatorId creatorId);
}
