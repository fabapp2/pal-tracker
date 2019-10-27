package io.pivotal.pal.tracker.jpa;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("!jdbc")
public interface TimeEntryJpaRepository extends JpaRepository<TimeEntry, Long> {
}
