package io.pivotal.pal.tracker.jdbc;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

@Profile("jdbc")
public interface TimeEntryJdbcRepository extends Repository<TimeEntry, Long> {
}
