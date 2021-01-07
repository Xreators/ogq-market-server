package com.ogqcorp.market.repository.persistence.hash;

import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;
import com.ogqcorp.market.domain.hash.ArtworkHashData;
import com.ogqcorp.market.domain.hash.ArtworkHashValue;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 11:03 오전 Last Modified At: 2020/12/17
 */
@Repository
public interface ArtworkHashDataRepository extends JpaRepository<ArtworkHashData, Long> {

  @Query("SELECT h FROM ArtworkHashData AS h  WHERE h.artworkHashValue = :value")
  Optional<ArtworkHashData> findFirstByArtworkHashValue(ArtworkHashValue value);

  List<ArtworkHashData> findByIdInAndDataStatus(List<Long> ids, ArtworkHashDataStatus dataStatus);
  Optional<ArtworkHashData> findFirstByArtworkHashValue(String value);


  Optional<List<ArtworkHashData>> findByIdGreaterThanAndDataStatus(long id, ArtworkHashDataStatus dataStatus, Pageable pageable);

  @Transactional
  @Modifying
  @Query(value = "update ArtworkHashData aHash set aHash.dataStatus = com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus.REQUEST, aHash"
      + ".updatedAt = :updatedAt where aHash.id in :ids and aHash.dataStatus = com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus.CREATED")
  int updateCreatedToRequestByIdIn(@Param("ids")List<Long> ids, @Param("updatedAt") ZonedDateTime updatedAt);
}