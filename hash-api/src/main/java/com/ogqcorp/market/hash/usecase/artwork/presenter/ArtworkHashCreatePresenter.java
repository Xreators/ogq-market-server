package com.ogqcorp.market.hash.usecase.artwork.presenter;

import com.ogqcorp.market.common.usecase.MonoPresenter;
import com.ogqcorp.market.hash.usecase.artwork.msg.output.ArtworkHashCreateOutMsg;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 5:57 오후 Last Modified At: 2020/12/16
 */
public interface ArtworkHashCreatePresenter<I extends ArtworkHashCreateOutMsg, O>
  extends MonoPresenter<I, O> {}
