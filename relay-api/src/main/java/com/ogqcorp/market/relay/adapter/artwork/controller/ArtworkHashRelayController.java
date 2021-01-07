package com.ogqcorp.market.relay.adapter.artwork.controller;

import com.ogqcorp.market.common.adapter.FluxAdapterController;
import com.ogqcorp.market.relay.adapter.artwork.dto.req.ArtworkHashRelayReqDTO;
import com.ogqcorp.market.relay.adapter.artwork.dto.res.ArtworkHashRelayResDTO;
import com.ogqcorp.market.relay.usecase.artwork.impl.ArtworkHashRelayUseCaseImpl;
import com.ogqcorp.market.relay.usecase.artwork.msg.input.ArtworkRelayInMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:43 오후 Last Modified At: 2020/12/18
 */
@Service
public class ArtworkHashRelayController implements
        FluxAdapterController<ArtworkHashRelayReqDTO, ArtworkHashRelayResDTO> {

  private final ArtworkHashRelayUseCaseImpl<ArtworkHashRelayResDTO> artworkHashRelayUseCase;

  @Autowired
  public ArtworkHashRelayController(
    final ArtworkHashRelayUseCaseImpl<ArtworkHashRelayResDTO> artworkHashRelayUseCase
  ) {
    this.artworkHashRelayUseCase = artworkHashRelayUseCase;
  }

  @Override
  public Flux<ArtworkHashRelayResDTO> execute(ArtworkHashRelayReqDTO input) {
    return artworkHashRelayUseCase.apply(new ArtworkRelayInMsg(input.getArtworkHashDataSeqIds()))
      .onErrorResume(Mono::error);
  }
}
