package com.ogqcorp.market.relay.adapter.artwork.presenter;

import com.ogqcorp.market.relay.adapter.artwork.dto.res.ArtworkHashRelayResDTO;
import com.ogqcorp.market.relay.usecase.artwork.msg.output.ArtworkRelayOutMsg;
import com.ogqcorp.market.relay.usecase.artwork.presenter.ArtworkHashRelayPresenter;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:45 오후 Last Modified At: 2020/12/18
 */
@Service
public class ArtworkHashRelayPresenterImpl implements
    ArtworkHashRelayPresenter<ArtworkRelayOutMsg, ArtworkHashRelayResDTO> {

  @Override
  public Flux<ArtworkHashRelayResDTO> apply(ArtworkRelayOutMsg outMsg) {
    return Mono.fromCallable(() -> new ArtworkHashRelayResDTO(outMsg.getArtworkId(), outMsg.getArtworkHashValue()))
      .flux();
  }
}
