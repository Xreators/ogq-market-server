package com.ogqcorp.market.hash.usecase.artwork.impl;

import com.ogqcorp.market.common.rpc.uci.response.UCIDocGetRes;
import com.ogqcorp.market.common.type.UniqueId;
import com.ogqcorp.market.common.util.TimeUtil;
import com.ogqcorp.market.domain.content.artwork.stockimage.StockImage;
import com.ogqcorp.market.hash.service.repo.content.ArtworkHashDataService.CreateArtworkHashData;
import com.ogqcorp.market.hash.service.repo.content.StockImageService.FindStockImageBy;
import com.ogqcorp.market.hash.service.repo.content.dto.ArtworkHashDataParamDTO;
import com.ogqcorp.market.hash.service.rpc.uci.UciDocService.GetUciDocBy;
import com.ogqcorp.market.hash.service.storage.FileHashService.GetFileSha256HashFrom;
import com.ogqcorp.market.hash.usecase.artwork.StockImageHashCreateUseCase;
import com.ogqcorp.market.hash.usecase.artwork.msg.intput.ArtworkHashCreateInMsg;
import com.ogqcorp.market.hash.usecase.artwork.msg.output.ArtworkHashCreateOutMsg;
import com.ogqcorp.market.hash.usecase.artwork.presenter.ArtworkHashCreatePresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 6:06 오후 Last Modified At: 2020/12/16
 */
@Service
public class StockImageHashCreateUseCaseImpl<O> implements
    StockImageHashCreateUseCase<ArtworkHashCreateInMsg, O> {

  private final FindStockImageBy<Long> findStockImageBy;
  private final GetFileSha256HashFrom<String> getFileSha256HashFrom;
  private final CreateArtworkHashData<ArtworkHashDataParamDTO> createArtworkHashData;
  private final GetUciDocBy<String, UCIDocGetRes> getUciDocBy;
  private final ArtworkHashCreatePresenter<ArtworkHashCreateOutMsg, O> presenter;
  private final String endPoint;

  @Autowired
  public StockImageHashCreateUseCaseImpl(
      final FindStockImageBy<Long> findStockImageBy,
      final GetFileSha256HashFrom<String> getFileSha256HashFrom,
      final CreateArtworkHashData<ArtworkHashDataParamDTO> createArtworkHashData,
      final GetUciDocBy<String, UCIDocGetRes> getUciDocBy,
      final ArtworkHashCreatePresenter<ArtworkHashCreateOutMsg, O> presenter,
      @Value("${storage.end-point}") String endPoint
  ) {
    this.findStockImageBy = findStockImageBy;
    this.getFileSha256HashFrom = getFileSha256HashFrom;
    this.createArtworkHashData = createArtworkHashData;
    this.getUciDocBy = getUciDocBy;
    this.presenter = presenter;
    this.endPoint = endPoint;
  }

  @Override
  public Mono<O> apply(ArtworkHashCreateInMsg inMsg) {
    return findStockImageBy.apply(inMsg.getArtworkSeqId())
      .flatMap(this::makeArtworkHashDataParam)
      .flatMap(createArtworkHashData::apply)
      .map(it -> new ArtworkHashCreateOutMsg(UniqueId.fromString(it.artworkId().toString()), it.jsonData(), it.artworkHashValue().toString()))
      .flatMap(presenter::apply)
      .onErrorResume(Mono::error);
  }
  public Mono<ArtworkHashDataParamDTO> makeArtworkHashDataParam(StockImage stockImage) {
    return Mono.zip(getFileSha256HashFrom.apply(stockImage.imageLocation(endPoint)), getUciDocBy.apply(stockImage.artworkId().toString()))
      .map(res -> ArtworkHashDataParamDTO.builder()
        .artworkId(stockImage.artworkId().toString())
        .creatorId(stockImage.creatorId().toString())
        .dataInsertedAt(TimeUtil.utcNow().toEpochSecond())
        .titles(stockImage.allTitles())
        .uciCode(res.getT2().getUciCode())
        .fileHash(res.getT1())
        .build()
      );
  }

}
