package com.ogqcorp.market.relay.service.repo.impl;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;

import com.ogqcorp.market.domain.hash.ArtworkRelayErrorLog;
import com.ogqcorp.market.relay.service.repo.ArtworkRelayErrorLogService.SaveArtworkRelayErrorLog;
import com.ogqcorp.market.relay.service.repo.dto.ErrorLogInsDTO;
import com.ogqcorp.market.repository.persistence.hash.ArtworkRelayErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:24 오전 Last Modified At: 2020/12/21
 */
@Service
public class SaveArtworkRelayErrorLogImpl implements SaveArtworkRelayErrorLog<ErrorLogInsDTO> {

  private final ArtworkRelayErrorLogRepository artworkRelayErrorLogRepository;

  @Autowired
  public SaveArtworkRelayErrorLogImpl(
      ArtworkRelayErrorLogRepository artworkRelayErrorLogRepository) {
    this.artworkRelayErrorLogRepository = artworkRelayErrorLogRepository;
  }

  @Override
  @Transactional(transactionManager = HASH_TRANSACTION_MANAGER)
  public Mono<ArtworkRelayErrorLog> apply(final ErrorLogInsDTO dto) {
    return Mono.fromCallable(() -> new ArtworkRelayErrorLog(dto.getTargetId(), dto.getErrorCode(), dto.getMessage()))
      .publishOn(Schedulers.boundedElastic())
      .map(artworkRelayErrorLogRepository::save)
      .onErrorResume(Mono::error);
  }
}
