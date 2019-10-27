package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class SharedTimeEntryRepositoryTestCases {

    private TimeEntryRepository repo;

    public SharedTimeEntryRepositoryTestCases(TimeEntryRepository repo) {
        this.repo = repo;
    }

    public void create() throws Exception {
        long projectId = 123L;
        long userId = 456L;
        TimeEntry createdTimeEntry = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        long timeEntryId = createdTimeEntry.getId();
        TimeEntry expected = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
        assertThat(createdTimeEntry).isEqualTo(expected);

        TimeEntry readEntry = repo.find(createdTimeEntry.getId());
        assertThat(readEntry).isEqualTo(expected);
    }

    
    public void find() throws Exception {
        long projectId = 123L;
        long userId = 456L;
        TimeEntry timeEntry = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        long timeEntryId = timeEntry.getId(); // the pk might differ, If the first test is run it is
        TimeEntry expected = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
        TimeEntry readEntry = repo.find(timeEntryId);
        assertThat(readEntry).isEqualTo(expected);
    }

    
    public void find_MissingEntry() {
        // InMemoryTimeEntryRepository repo = new InMemoryTimeEntryRepository();

        long timeEntryId = 1L;

        TimeEntry readEntry = repo.find(timeEntryId);
        assertThat(readEntry).isNull();
    }

    
    public void list() throws Exception {
        TimeEntry timeEntry1 = repo.create(new TimeEntry(123L, 456L, LocalDate.parse("2017-01-08"), 8));
        TimeEntry timeEntry2 = repo.create(new TimeEntry(789L, 654L, LocalDate.parse("2017-01-07"), 4));

        List<TimeEntry> expected = asList(
                timeEntry1, timeEntry2
        );
        assertThat(expected).containsExactlyInAnyOrderElementsOf(repo.list());
    }

    
    public void update() throws Exception {
        TimeEntry created = repo.create(new TimeEntry(123L, 456L, LocalDate.parse("2017-01-08"), 8));

        TimeEntry updatedEntry = repo.update(
                created.getId(),
                new TimeEntry(321L, 654L, LocalDate.parse("2017-01-09"), 5));

        TimeEntry expected = new TimeEntry(created.getId(), 321L, 654L, LocalDate.parse("2017-01-09"), 5);
        assertThat(updatedEntry).isEqualTo(expected);
        assertThat(repo.find(created.getId())).isEqualTo(expected);
    }

    
    public void updaMissingEntry() {
        TimeEntry updatedEntry = repo.update(
                1L,
                new TimeEntry(321L, 654L, LocalDate.parse("2017-01-09"), 5));

        assertThat(updatedEntry).isNull();
    }

    
    public void delete() throws Exception {
        long projectId = 123L;
        long userId = 456L;
        TimeEntry created = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        repo.delete(created.getId());
        assertThat(repo.list()).isEmpty();
    }

    
    public void deleteKeepsTrackOfLatestIdProperly() {
        long projectId = 123L;
        long userId = 456L;
        TimeEntry created = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));
        repo.delete(created.getId());

        TimeEntry createdSecond = repo.create(new TimeEntry(projectId, userId, LocalDate.parse("2017-01-08"), 8));

        assertThat(createdSecond.getId()).isEqualTo(created.getId() + 1);
    }
}
