package io.pivotal.pal.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@DataJdbcTest
@ActiveProfiles("jdbc")
public class TimeEntryJdbcRepositoryTests /*extends SharedTimeEntryRepositoryTestCases*/ {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TimeEntryRepository repo;

    private SharedTimeEntryRepositoryTestCases sharedTimeEntryRepositoryTestCases;

    @Before
    public void setup() {
        initializeDatabase();
        sharedTimeEntryRepositoryTestCases = new SharedTimeEntryRepositoryTestCases(repo);
    }

    private void initializeDatabase() {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS time_entries (\n" +
                "  id         BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                "  project_id BIGINT(20),\n" +
                "  user_id    BIGINT(20),\n" +
                "  date       DATE,\n" +
                "  hours      INT,\n" +
                "\n" +
                "  PRIMARY KEY (id)\n" +
                ")");
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