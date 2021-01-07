package com.ogqcorp.market.hash.adapter.artwork.controller;

import com.ogqcorp.market.common.adapter.MonoAdapterController;
import com.ogqcorp.market.common.type.artwork.ArtworkType;
import com.ogqcorp.market.common.usecase.MonoUseCase;
import com.ogqcorp.market.hash.adapter.artwork.dto.req.ArtworkHashCreateReqDTO;
import com.ogqcorp.market.hash.adapter.artwork.dto.res.ArtworkHashCreateResDTO;
import com.ogqcorp.market.hash.usecase.artwork.StockImageHashCreateUseCase;
import com.ogqcorp.market.hash.usecase.artwork.msg.intput.ArtworkHashCreateInMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:25 오후 Last Modified At: 2020/12/16
 */
@Service
public class ArtworkHashCreateController implements MonoAdapterController<ArtworkHashCreateReqDTO, ArtworkHashCreateResDTO> {

  private StockImageHashCreateUseCase<ArtworkHashCreateInMsg, ArtworkHashCreateResDTO> stockImageHashCreateUseCase;

  @Autowired
  public ArtworkHashCreateController(
      StockImageHashCreateUseCase<ArtworkHashCreateInMsg, ArtworkHashCreateResDTO> stockImageHashCreateUseCase) {
    this.stockImageHashCreateUseCase = stockImageHashCreateUseCase;
  }

  @Override
  public Mono<ArtworkHashCreateResDTO> execute(ArtworkHashCreateReqDTO input) {
    return getCorrectUseCase(input.getArtworkType())
      .apply(new ArtworkHashCreateInMsg(input.getArtworkSeqId()))
      .onErrorResume(Mono::error);
  }
  public MonoUseCase<ArtworkHashCreateInMsg, ArtworkHashCreateResDTO> getCorrectUseCase(ArtworkType artworkType) {
    // not yet Others
    return stockImageHashCreateUseCase;
  }
}
