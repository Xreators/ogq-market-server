package com.ogqcorp.market.hash.service.repo.content.impl;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;

import com.google.gson.Gson;
import com.ogqcorp.market.common.util.HashUtils;
import com.ogqcorp.market.common.util.JsonConverter;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.domain.content.artwork.ArtworkId;
import com.ogqcorp.market.domain.content.uci.UCICode;
import com.ogqcorp.market.domain.hash.ArtworkHashData;
import com.ogqcorp.market.domain.hash.ArtworkHashValue;
import com.ogqcorp.market.hash.service.repo.content.ArtworkHashDataService.CreateArtworkHashData;
import com.ogqcorp.market.hash.service.repo.content.dto.ArtworkHashDataParamDTO;
import com.ogqcorp.market.hash.service.repo.content.exception.DuplicateArtworkHashException;
import com.ogqcorp.market.hash.service.repo.content.exception.InvalidArtworkHashDataException;
import com.ogqcorp.market.repository.persistence.hash.ArtworkHashDataRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 1:20 오후 Last Modified At: 2020/12/17
 */
@Service
public class CreateArtworkHashDataImpl implements CreateArtworkHashData<ArtworkHashDataParamDTO> {

  private final ArtworkHashDataRepository artworkHashDataRepository;
  private final Gson gson;

  @Autowired
  public CreateArtworkHashDataImpl(
    ArtworkHashDataRepository artworkHashDataRepository,
    Gson gson
  ) {
    this.artworkHashDataRepository = artworkHashDataRepository;
    this.gson = gson;
  }

  @Override
  @Transactional(transactionManager = HASH_TRANSACTION_MANAGER)
  public Mono<ArtworkHashData> apply(ArtworkHashDataParamDTO paramDTO) {
    return Mono.fromCallable(() -> createDomain(paramDTO))
      .publishOn(Schedulers.boundedElastic())
      .flatMap(this::saveDomain)
      .onErrorMap(err -> {
        if (err instanceof DataIntegrityViolationException && err.getMessage().contains("constraint")) {
          return new DuplicateArtworkHashException(err.getMessage());
        }
        return err;
      })
      .onErrorResume(Mono::error);
  }
  private Mono<ArtworkHashData> saveDomain(ArtworkHashData artworkHashData) {
    return Mono.fromCallable(() -> artworkHashDataRepository.save(artworkHashData));
  }
  public ArtworkHashData createDomain(ArtworkHashDataParamDTO param) {
    checkSavable(param);
    String jsonData = JsonConverter.convertJsonStringFromObject(gson, param);
    return new ArtworkHashData(
      CreatorId.fromString(param.getCreatorId()),
      ArtworkId.fromString(param.getArtworkId()),
      UCICode.fromString(param.getUciCode()),
      JsonConverter.convertJsonStringFromObject(gson, param.getTitles()),
      param.getFileHash(),
      param.getDataInsertedAt(),
      jsonData,
      ArtworkHashValue.fromString(HashUtils.getSha256Hex(jsonData))
    );
  }
  void checkSavable(ArtworkHashDataParamDTO param) {
    Optional.ofNullable(param.getCreatorId())
        .orElseThrow(() -> new InvalidArtworkHashDataException("CreatorId is Null"));
    Optional.ofNullable(param.getArtworkId())
        .orElseThrow(() -> new InvalidArtworkHashDataException("ArtworkId is Null"));
    Optional.ofNullable(param.getTitles()).ifPresentOrElse(
      titles -> {
        if (titles.size() < 1) throw new InvalidArtworkHashDataException("Titles is Empty");
      },
      () -> {
        throw new InvalidArtworkHashDataException("Titles is Null");
      }
    );
    Optional.ofNullable(param.getUciCode())
        .orElseThrow(() -> new InvalidArtworkHashDataException("uciCode is Null"));
    Optional.ofNullable(param.getFileHash())
        .orElseThrow(() -> new InvalidArtworkHashDataException("FileHash is Null"));
    Optional.ofNullable(param.getDataInsertedAt())
        .orElseThrow(() -> new InvalidArtworkHashDataException("DataInsertedAt is Null"));
  }
}
