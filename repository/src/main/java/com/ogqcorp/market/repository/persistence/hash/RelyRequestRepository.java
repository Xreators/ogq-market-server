package com.ogqcorp.market.repository.persistence.hash;

import com.ogqcorp.market.common.type.task.RelayStatus;
import com.ogqcorp.market.domain.hash.ArtworkRelayRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelyRequestRepository extends JpaRepository<ArtworkRelayRequest, Long> {
  Optional<ArtworkRelayRequest> findFirstByRequestStatusOrderByIdAsc(RelayStatus requestStatus);
}
