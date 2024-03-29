package io.pivotal.pal.tracker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TimeEntryJpaRepositoryTest /*extends SharedTimeEntryRepositoryTestCases*/ {

    SharedTimeEntryRepositoryTestCases sharedTimeEntryRepositoryTestCases;

    @Autowired
    private TimeEntryRepository repo;

    @Before
    public void setup() {
        sharedTimeEntryRepositoryTestCases = new SharedTimeEntryRepositoryTestCases(repo);
    }

    @Test
    public void create() throws Exception {
        sharedTimeEntryRepositoryTestCases.create();
    }

    @Test
    public void find() throws Exception {
        sharedTimeEntryRepositoryTestCases.find();
    }

    @Test
    public void find_MissingEntry() {
        sharedTimeEntryRepositoryTestCases.find_MissingEntry();
    }

    @Test
    public void list() throws Exception {
        sharedTimeEntryRepositoryTestCases.list();
    }

    @Test
    public void update() throws Exception {
        sharedTimeEntryRepositoryTestCases.update();
    }

    @Test
    public void updaMissingEntry() {
        sharedTimeEntryRepositoryTestCases.updaMissingEntry();
    }

    @Test
    public void delete() throws Exception {
        sharedTimeEntryRepositoryTestCases.delete();
    }

    @Test
    public void deleteKeepsTrackOfLatestIdProperly() {
        sharedTimeEntryRepositoryTestCases.deleteKeepsTrackOfLatestIdProperly();
    }

}
