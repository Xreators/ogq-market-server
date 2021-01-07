package com.ogqcorp.market.repository.persistence.content.artwork;

import com.ogqcorp.market.domain.content.artwork.stockimage.StockImage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 5:37 오후 Last Modified At: 2020/12/09
 */
@Repository
public interface StockImageRepository extends JpaRepository<StockImage, Long> {

  @Query("SELECT DISTINCT h FROM StockImage AS h JOIN FETCH h.artworkTextContents WHERE h.id = :id")
  Optional<StockImage> findBySeqId(long id);

}
