package com.ogqcorp.market.relay.usecase.artwork.presenter;

import com.ogqcorp.market.common.usecase.FluxPresenter;
import com.ogqcorp.market.relay.usecase.artwork.msg.output.ArtworkRelayOutMsg;


/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:39 오후 Last Modified At: 2020/12/18
 */
public interface ArtworkHashRelayPresenter<I extends ArtworkRelayOutMsg, O>
    extends FluxPresenter<I, O> {}
