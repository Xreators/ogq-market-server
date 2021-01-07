package com.ogqcorp.market.hash.usecase.artwork;

import com.ogqcorp.market.common.usecase.MonoUseCase;
import com.ogqcorp.market.hash.usecase.artwork.msg.intput.ArtworkHashCreateInMsg;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:49 오후 Last Modified At: 2020/12/16
 */
public interface StockImageHashCreateUseCase<I extends ArtworkHashCreateInMsg, O>
  extends MonoUseCase<I, O> {}
