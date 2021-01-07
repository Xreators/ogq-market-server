package com.ogqcorp.market.relay.usecase.artwork;

import com.ogqcorp.market.common.usecase.FluxUseCase;
import com.ogqcorp.market.relay.usecase.artwork.msg.input.ArtworkRelayInMsg;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:37 오후 Last Modified At: 2020/12/18
 */
public interface ArtworkHashRelayUseCase<I extends ArtworkRelayInMsg, O>
  extends FluxUseCase<I, O> {}
