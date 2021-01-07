package com.ogqcorp.market.repository.persistence.account;

import static com.ogqcorp.market.common.constants.TransactionManager.ACCOUNT_TRANSACTION_MANAGER;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.ogqcorp.market.domain.account.creator.Creator;
import com.ogqcorp.market.domain.account.creator.CreatorId;
import com.ogqcorp.market.repository.config.DefaultConfig;
import com.ogqcorp.market.repository.config.TestAccountJpaConfig;
import com.ogqcorp.market.repository.config.TestAccountJpaConfig.TestAccountRepoConfig;
import com.ogqcorp.market.repository.config.TestDataSources;
import java.util.Optional;
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
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 2:58 오후 Last Modified At: 2020/12/09
 */

@Profile(value = {"dev","test"})
@ActiveProfiles("test")
@DataJpaTest
@Import({DefaultConfig.class, TestAccountJpaConfig.class, TestAccountRepoConfig.class, TestDataSources.class})
@AutoConfigureTestDatabase(replace = NONE)
@Transactional(transactionManager = ACCOUNT_TRANSACTION_MANAGER)
@TestConstructor(autowireMode = AutowireMode.ALL)
@Disabled("CreatorRepository Test 비활성화")
public class CreatorRepositoryTest {
  private Logger logger = LoggerFactory.getLogger(CreatorRepositoryTest.class);

  private CreatorRepository creatorRepository;

  public CreatorRepositoryTest(
      CreatorRepository creatorRepository) {
    this.creatorRepository = creatorRepository;
  }

  @Test
  public void findByCreatorId__TEST() {
    Optional<Creator> op = creatorRepository.findByCreatorId(CreatorId.fromString("5698da40ee670"));
    op.ifPresentOrElse(
      (creator) -> logger.debug("{}", creator),
      () -> Assertions.fail()
    );
  }

}
