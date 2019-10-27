package io.pivotal.pal.tracker.jdbc;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Component
@Profile("jdbc")
public class TimeEntryJdbcRepositoryImpl implements
        TimeEntryRepository {

    private static final String INSERT_SQL = "INSERT INTO time_entries (project_id, user_id, date, hours) VALUES (?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE time_entries SET project_id = ?, user_id = ?, date = ?, hours = ? WHERE id = ?";
    private static final String FIND_SQL = "SELECT * FROM time_entries WHERE ID=?";
    private static final String LIST_SQL = "SELECT * FROM time_entries";
    private static final String DELETE_SQL = "DELETE FROM time_entries WHERE ID = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<TimeEntry> rowMapper = (rs, rowNum) -> {
        TimeEntry timeEntry = new TimeEntry(
                rs.getLong("ID"),
                rs.getLong("PROJECT_ID"),
                rs.getLong("USER_ID"),
                rs.getDate("DATE").toLocalDate(),
                rs.getInt("HOURS")
        );
        return timeEntry;
    };

    public TimeEntryJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, timeEntry.getProjectId());
            ps.setLong(2, timeEntry.getUserId());
            ps.setDate(3, Date.valueOf(timeEntry.getDate()));
            ps.setInt(4, timeEntry.getHours());
            return ps;
        }, keyHolder);
        timeEntry.setId((long) keyHolder.getKey());
        return timeEntry;
    }


    public TimeEntry find(Long id) {
        try {
            TimeEntry timeEntry = jdbcTemplate.queryForObject(FIND_SQL, rowMapper, id);
            return timeEntry;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<TimeEntry> list() {
        return jdbcTemplate.query(LIST_SQL, rowMapper);
    }


    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry timeEntryFound = find(id);
        if (timeEntryFound != null) {
            TimeEntry timeEntryCloned = createClone(id, timeEntry);
            this.jdbcTemplate.update(UPDATE_SQL, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours(), id);
            timeEntry.setId(id);
            return timeEntry;
        } else {
            return null;
        }
    }


    public void delete(Long id) {
        this.jdbcTemplate.update(DELETE_SQL, id);
    }


    private TimeEntry createClone(Long id, TimeEntry timeEntry) {
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        return updatedEntry;
    }

}

