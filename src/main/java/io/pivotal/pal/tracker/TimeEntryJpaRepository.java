package io.pivotal.pal.tracker;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TimeEntryJpaRepository extends JpaRepository<TimeEntry, Long> {
}
