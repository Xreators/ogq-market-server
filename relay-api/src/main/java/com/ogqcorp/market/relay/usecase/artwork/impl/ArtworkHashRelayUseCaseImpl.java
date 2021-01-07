package com.ogqcorp.market.relay.usecase.artwork.impl;

import com.ogqcorp.market.common.amqp.msg.hash.ArtworkRelayResultAMQPMsg;
import com.ogqcorp.market.common.type.UniqueId;
import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;
import com.ogqcorp.market.infrastucture.amqp.producer.MarketAMQPProducer;
import com.ogqcorp.market.relay.service.repo.ArtworkHashDataService.GetRelayReqListByIds;
import com.ogqcorp.market.relay.service.repo.ArtworkHashDataService.UpdateArtworkHashDataTo;
import com.ogqcorp.market.relay.service.repo.ArtworkHashService.SaveArtworkHashes;
import com.ogqcorp.market.relay.service.repo.ArtworkRelayErrorLogService.SaveArtworkRelayErrorLog;
import com.ogqcorp.market.relay.service.repo.dto.ArtworkHashDataUpdDTO;
import com.ogqcorp.market.relay.service.repo.dto.ArtworkHashInsDTO;
import com.ogqcorp.market.relay.service.repo.dto.ErrorLogInsDTO;
import com.ogqcorp.market.relay.service.rpc.OntologyService.BatchHashes;
import com.ogqcorp.market.relay.service.rpc.dto.HashesDTO;
import com.ogqcorp.market.relay.usecase.artwork.ArtworkHashRelayUseCase;
import com.ogqcorp.market.relay.usecase.artwork.msg.input.ArtworkRelayInMsg;
import com.ogqcorp.market.relay.usecase.artwork.msg.output.ArtworkRelayOutMsg;
import com.ogqcorp.market.relay.usecase.artwork.presenter.ArtworkHashRelayPresenter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:46 오후 Last Modified At: 2020/12/18
 */
@Service
public class ArtworkHashRelayUseCaseImpl<O> implements
    ArtworkHashRelayUseCase<ArtworkRelayInMsg, O> {

  private final GetRelayReqListByIds<List<Long>> getRelayReqListByIds;
  private final BatchHashes<HashesDTO, Void> batchHashes;
  private final SaveArtworkHashes<List<ArtworkHashInsDTO>> saveArtworkHashes;
  private final UpdateArtworkHashDataTo<ArtworkHashDataUpdDTO> updateArtworkHashDataTo;
  private final MarketAMQPProducer<ArtworkRelayResultAMQPMsg, Void> artworkRelayAMQPProducer;
  private final ArtworkHashRelayPresenter<ArtworkRelayOutMsg, O> presenter;
  private final SaveArtworkRelayErrorLog<ErrorLogInsDTO> saveArtworkRelayErrorLog;

  @Autowired
  public ArtworkHashRelayUseCaseImpl(
    final GetRelayReqListByIds<List<Long>> getRelayReqListByIds,
    final BatchHashes<HashesDTO, Void> batchHashes,
    final SaveArtworkHashes<List<ArtworkHashInsDTO>> saveArtworkHashes,
    final UpdateArtworkHashDataTo<ArtworkHashDataUpdDTO> updateArtworkHashDataTo,
    final MarketAMQPProducer<ArtworkRelayResultAMQPMsg, Void> artworkRelayAMQPProducer,
    final ArtworkHashRelayPresenter<ArtworkRelayOutMsg, O> presenter,
    final SaveArtworkRelayErrorLog<ErrorLogInsDTO> saveArtworkRelayErrorLog
  ) {
    this.getRelayReqListByIds = getRelayReqListByIds;
    this.batchHashes = batchHashes;
    this.saveArtworkHashes = saveArtworkHashes;
    this.updateArtworkHashDataTo = updateArtworkHashDataTo;
    this.artworkRelayAMQPProducer = artworkRelayAMQPProducer;
    this.presenter = presenter;
    this.saveArtworkRelayErrorLog = saveArtworkRelayErrorLog;
  }

  @Override
  public Flux<O> apply(ArtworkRelayInMsg inMsg) {
    return getRelayReqListByIds.apply(inMsg.getArtworkHashDataSeqIds())
      .flatMap(hashes -> batchHashes.apply(new HashesDTO(hashes.stream()
          .map(it -> it.artworkHashValue().toString())
          .collect(Collectors.toUnmodifiableList()))
        ).thenReturn(hashes)
      )
      .flatMapMany(hashes -> saveArtworkHashes.apply(hashes.stream()
        .map(it -> new ArtworkHashInsDTO(UniqueId.fromString(it.artworkId().toString()), it.artworkHashValue().toString()))
        .collect(Collectors.toUnmodifiableList())
      ))
      .flatMap(it ->
        Mono.zip(
          updateArtworkHashDataTo.apply(new ArtworkHashDataUpdDTO(it.artworkHash().toString(), ArtworkHashDataStatus.SUCCESS)),
          artworkRelayAMQPProducer.produce(new ArtworkRelayResultAMQPMsg(it.artworkId().toString(), it.artworkHash().toString()))
        ).thenReturn(new ArtworkRelayOutMsg(UniqueId.fromString(it.artworkId().toString()), it.artworkHash().toString()))
      )
      .flatMap(presenter)
      .onErrorResume(Flux::error);
  }
}
