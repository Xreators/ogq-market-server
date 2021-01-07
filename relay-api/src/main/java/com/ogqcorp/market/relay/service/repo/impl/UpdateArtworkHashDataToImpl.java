package com.ogqcorp.market.relay.service.repo.impl;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;

import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;
import com.ogqcorp.market.domain.hash.ArtworkHashData;
import com.ogqcorp.market.domain.hash.ArtworkHashValue;
import com.ogqcorp.market.relay.service.repo.ArtworkHashDataService.UpdateArtworkHashDataTo;
import com.ogqcorp.market.relay.service.repo.dto.ArtworkHashDataUpdDTO;
import com.ogqcorp.market.repository.error.exception.content.NotFoundArtworkHashData;
import com.ogqcorp.market.repository.persistence.hash.ArtworkHashDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/28 12:08 오후 Last Modified At: 2020/12/28
 */
@Service
public class UpdateArtworkHashDataToImpl implements UpdateArtworkHashDataTo<ArtworkHashDataUpdDTO> {
  private ArtworkHashDataRepository artworkHashDataRepository;

  @Autowired
  public UpdateArtworkHashDataToImpl(
      ArtworkHashDataRepository artworkHashDataRepository) {
    this.artworkHashDataRepository = artworkHashDataRepository;
  }

  @Override
  @Transactional(transactionManager = HASH_TRANSACTION_MANAGER)
  public Mono<ArtworkHashData> apply(ArtworkHashDataUpdDTO artworkHashDataUpdDTO) {
    return Mono.fromCallable(() -> artworkHashDataUpdDTO)
      .publishOn(Schedulers.boundedElastic())
      .map(this::update)
      .onErrorResume(Mono::error);
  }
  ArtworkHashData update(ArtworkHashDataUpdDTO dto) {
    ArtworkHashData hashData = artworkHashDataRepository.findFirstByArtworkHashValue(ArtworkHashValue.fromString(dto.getArtworkHash()))
      .orElseThrow(() -> new NotFoundArtworkHashData(dto.getArtworkHash()));
    hashData.updateStatus(dto.getStatus());
    artworkHashDataRepository.save(hashData);
    return hashData;
  }


}
