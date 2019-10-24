package io.pivotal.pal.tracker;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("jdbc")
public class TimeEntryJdbcRepositoryTest extends BaseTimeEntryRepsitoryTest {

}
