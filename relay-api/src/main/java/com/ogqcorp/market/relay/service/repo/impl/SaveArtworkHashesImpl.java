package com.ogqcorp.market.relay.service.repo.impl;

import static com.ogqcorp.market.common.constants.TransactionManager.CONTENT_TRANSACTION_MANAGER;

import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.artwork.ArtworkHash;
import com.ogqcorp.market.domain.hash.ArtworkHashValue;
import com.ogqcorp.market.relay.service.repo.ArtworkHashService.SaveArtworkHashes;
import com.ogqcorp.market.relay.service.repo.dto.ArtworkHashInsDTO;
import com.ogqcorp.market.repository.persistence.content.ArtworkHashRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 11:44 오전 Last Modified At: 2020/12/21
 */
@Service
public class SaveArtworkHashesImpl implements SaveArtworkHashes<List<ArtworkHashInsDTO>> {

  private final ArtworkHashRepository artworkHashRepository;

  @Autowired
  public SaveArtworkHashesImpl(
      ArtworkHashRepository artworkHashRepository) {
    this.artworkHashRepository = artworkHashRepository;
  }

  @Override
  @Transactional(transactionManager = CONTENT_TRANSACTION_MANAGER)
  public Flux<ArtworkHash> apply(List<ArtworkHashInsDTO> hashes) {
    return Mono.fromCallable(
      () -> hashes.stream()
        .map(it -> new ArtworkHash(
          ArtworkId.fromUniqueId(it.getUniqueId()),
          ArtworkHashValue.fromString(it.getHash())))
        .collect(Collectors.toUnmodifiableList())
      )
      .publishOn(Schedulers.boundedElastic())
      .map(artworkHashRepository::saveAll)
      .flatMapIterable(it -> it)
      .onErrorResume(Flux::error);
  }
}
