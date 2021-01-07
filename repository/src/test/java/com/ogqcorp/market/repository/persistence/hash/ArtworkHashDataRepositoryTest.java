package com.ogqcorp.market.repository.persistence.hash;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.ogqcorp.market.common.type.hash.ArtworkHashDataStatus;
import com.ogqcorp.market.domain.hash.ArtworkHashData;
import com.ogqcorp.market.domain.hash.ArtworkHashValue;
import com.ogqcorp.market.repository.config.DefaultConfig;
import com.ogqcorp.market.repository.config.TestDataSources;
import com.ogqcorp.market.repository.config.TestHashJpaConfig;
import com.ogqcorp.market.repository.config.TestHashJpaConfig.TestHashRepoConfig;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:53 오후 Last Modified At: 2020/12/18
 */
@Profile(value = {"dev","test"})
@ActiveProfiles("test")
@DataJpaTest
@Import({
  DefaultConfig.class,
  TestHashJpaConfig.class, TestHashRepoConfig.class, TestDataSources.class
})
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(transactionManager = HASH_TRANSACTION_MANAGER)
@TestConstructor(autowireMode = AutowireMode.ALL)
@Disabled("StockImageRepository Test 비활성화")
public class ArtworkHashDataRepositoryTest {
  private Logger logger = LoggerFactory.getLogger(ArtworkHashDataRepositoryTest.class);

  private ArtworkHashDataRepository artworkHashDataRepository;

  public ArtworkHashDataRepositoryTest(
      ArtworkHashDataRepository artworkHashDataRepository) {
    this.artworkHashDataRepository = artworkHashDataRepository;
  }


  @Test
  void findByIdInAndRelayStatus__TEST() {
    List<Long> ids = Arrays.asList(1220L,1221L,1230L,1231L);
    List<ArtworkHashData> dataList = artworkHashDataRepository.findByIdInAndDataStatus(ids, ArtworkHashDataStatus.CREATED);

    logger.debug("{}", dataList.stream().map(ArtworkHashData::artworkId).collect(Collectors.toList()));
  }


  @Test
  void findFirstByArtworkHashValue__TEST() {
    String value = "8b4c93ac6732ac59e1e2097d4d7a41a703e2050b37444d825f0f8a7b437c0c4c";
    Optional<ArtworkHashData> data = artworkHashDataRepository.findFirstByArtworkHashValue(
        ArtworkHashValue.fromString(value));
    data.ifPresentOrElse(
      (hash) -> {
        logger.debug("{}", hash.artworkHashValue().toString());
        Assertions.assertEquals(value, hash.artworkHashValue().toString());
      },
      (  ) -> Assertions.fail()
    );
  }
}
