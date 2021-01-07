package com.ogqcorp.market.relay.service.repo.impl;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;

import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;
import com.ogqcorp.market.domain.hash.ArtworkHashData;
import com.ogqcorp.market.relay.service.repo.ArtworkHashDataService.GetRelayReqListByIds;
import com.ogqcorp.market.repository.persistence.hash.ArtworkHashDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 10:36 오전 Last Modified At: 2020/12/21
 */
@Service
public class GetRelayReqListByIdsImpl implements
    GetRelayReqListByIds<List<Long>> {

  private final ArtworkHashDataRepository artworkHashDataRepository;

  @Autowired
  public GetRelayReqListByIdsImpl(
      ArtworkHashDataRepository artworkHashDataRepository) {
    this.artworkHashDataRepository = artworkHashDataRepository;
  }

  @Override
  @Transactional(transactionManager = HASH_TRANSACTION_MANAGER)
  public Mono<List<ArtworkHashData>> apply(List<Long> req) {
    return Mono.fromCallable(() -> req)
      .publishOn(Schedulers.boundedElastic())
      .map(ids -> artworkHashDataRepository.findByIdInAndDataStatus(ids, ArtworkHashDataStatus.REQUEST))
      .onErrorResume(Mono::error);
  }
}
