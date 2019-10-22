package io.pivotal.pal.tracker;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = PalTrackerApplication.class ) // onlz required because package of tests differ from packages of prod code
@DataJpaTest
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // JUnit does noit guarantee anz execution order but order is important here (because of pk values we expect)
//@Rollback(true) // thought that's the default ?!
//@ComponentScan
public class InMemoryTimeEntryJpaRepositoryTest {

    @Autowired
    TimeEntryRepository repo;

//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Autowired
//    TimeEntryJpaRepository timeEntryJpaRepository;

//    @Test
//    public void foo(){
//        System.out.println("YES");
//        ExtendedTimeEntryRepository bean = applicationContext.getBean(ExtendedTimeEntryRepository.class);
//        System.out.println(bean);
//    }

    @Test
    public void t1_create() throws Exception {
        // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        long projectId = 123L;
        long userId = 456L;
        TimeEntry createdTimeEntry = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        long timeEntryId = createdTimeEntry.getId();
        TimeEntry expected = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
        assertThat(createdTimeEntry).isEqualTo(expected);

        TimeEntry readEntry = repo.find(createdTimeEntry.getId());
        assertThat(readEntry).isEqualTo(expected);
    }

    @Test
    @Rollback(true)
    public void t2_find() throws Exception {
        // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        long projectId = 123L;
        long userId = 456L;
        TimeEntry timeEntry = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        long timeEntryId = timeEntry.getId(); // the pk might differ, If the first test is run it is
        TimeEntry expected = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
        TimeEntry readEntry = repo.find(timeEntryId);
        assertThat(readEntry).isEqualTo(expected);
    }

    @Test
    public void t3_find_MissingEntry() {
        // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        long timeEntryId = 1L;

        TimeEntry readEntry = repo.find(timeEntryId);
        assertThat(readEntry).isNull();
    }

    @Test
    public void t4_list() throws Exception {
        // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry timeEntry1 = repo.create(new TimeEntry(123L, 456L, LocalDate.parse("2017-01-08"), 8));
        TimeEntry timeEntry2 = repo.create(new TimeEntry(789L, 654L, LocalDate.parse("2017-01-07"), 4));

        List<TimeEntry> expected = asList(
                timeEntry1, timeEntry2
//                new TimeEntry(1L, 123L, 456L, LocalDate.parse("2017-01-08"), 8),
//                new TimeEntry(2L, 789L, 654L, LocalDate.parse("2017-01-07"), 4)
        );
        assertThat(expected).containsExactlyInAnyOrderElementsOf(repo.list());
    }

    @Test
    public void t5_update() throws Exception {
        // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();
        TimeEntry created = repo.create(new TimeEntry(123L, 456L, LocalDate.parse("2017-01-08"), 8));

        TimeEntry updatedEntry = repo.update(
                created.getId(),
                new TimeEntry(321L, 654L, LocalDate.parse("2017-01-09"), 5));

        TimeEntry expected = new TimeEntry(created.getId(), 321L, 654L, LocalDate.parse("2017-01-09"), 5);
        assertThat(updatedEntry).isEqualTo(expected);
        assertThat(repo.find(created.getId())).isEqualTo(expected);
    }

    @Test
    public void t6_update_MissingEntry() {
       // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        TimeEntry updatedEntry = repo.update(
                1L,
                new TimeEntry(321L, 654L, LocalDate.parse("2017-01-09"), 5));

        assertThat(updatedEntry).isNull();
    }

    @Test
    public void t7_delete() throws Exception {
       //  InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        long projectId = 123L;
        long userId = 456L;
        TimeEntry created = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        repo.delete(created.getId());
        assertThat(repo.list()).isEmpty();
    }

    @Test
    @Ignore("Thwe database takes care of this!")
    public void t8_deleteKeepsTrackOfLatestIdProperly() {
       //  InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        long projectId = 123L;
        long userId = 456L;
        TimeEntry created = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

//        assertThat(created.getId()).isEqualTo(1);

        repo.delete(created.getId());

        TimeEntry createdSecond = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        assertThat(createdSecond.getId()).isEqualTo(2);
    }
}
