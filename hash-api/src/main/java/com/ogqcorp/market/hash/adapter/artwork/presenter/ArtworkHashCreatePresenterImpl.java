package com.ogqcorp.market.hash.adapter.artwork.presenter;

import com.ogqcorp.market.hash.adapter.artwork.dto.res.ArtworkHashCreateResDTO;
import com.ogqcorp.market.hash.usecase.artwork.msg.output.ArtworkHashCreateOutMsg;
import com.ogqcorp.market.hash.usecase.artwork.presenter.ArtworkHashCreatePresenter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 6:04 오후 Last Modified At: 2020/12/16
 */
@Service
public class ArtworkHashCreatePresenterImpl implements
  ArtworkHashCreatePresenter<ArtworkHashCreateOutMsg, ArtworkHashCreateResDTO> {

  @Override
  public Mono<ArtworkHashCreateResDTO> apply(ArtworkHashCreateOutMsg outMsg) {
    return Mono.fromCallable(() -> convert(outMsg))
      .onErrorResume(Mono::error);
  }
  private ArtworkHashCreateResDTO convert(ArtworkHashCreateOutMsg outMsg) {
    return ArtworkHashCreateResDTO.builder()
      .artworkId(outMsg.getArtworkId().idString())
      .hashData(outMsg.getHashData())
      .plainData(outMsg.getPlainData())
      .build();
  }
}
