package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long>, ExtendedTimeEntryRepository {
}
