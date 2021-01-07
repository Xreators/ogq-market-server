package com.ogqcorp.market.hash.service.repo.content.impl;

import com.ogqcorp.market.hash.service.repo.content.ArtworkHashDataService.ExistArtworkHashData;
import com.ogqcorp.market.repository.persistence.hash.ArtworkHashDataRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 4:56 오후 Last Modified At: 2020/12/17
 */
@Service
public class ExistArtworkHashDataByHashValue implements ExistArtworkHashData<String> {

  private final ArtworkHashDataRepository artworkHashDataRepository;

  @Autowired
  public ExistArtworkHashDataByHashValue(
      ArtworkHashDataRepository artworkHashDataRepository) {
    this.artworkHashDataRepository = artworkHashDataRepository;
  }

  @Override
  public Mono<Boolean> apply(String value) {
    return Mono.fromCallable(() -> value)
      .publishOn(Schedulers.boundedElastic())
      .map(artworkHashDataRepository::findFirstByArtworkHashValue)
      .map(Optional::isPresent)
      .onErrorResume(Mono::error);
  }
}
