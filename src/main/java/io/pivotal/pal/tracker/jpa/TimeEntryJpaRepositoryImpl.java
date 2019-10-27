package io.pivotal.pal.tracker.jpa;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("!jdbc")
public class TimeEntryJpaRepositoryImpl implements TimeEntryRepository {

    @Autowired
    private TimeEntryJpaRepository timeEntryRepository;


    public TimeEntry create(TimeEntry timeEntry) {
        return timeEntryRepository.save(timeEntry);
    }


    public TimeEntry find(Long id) {
        Optional<TimeEntry> byId = timeEntryRepository.findById(id);
        return byId.orElse(null);
    }


    public List<TimeEntry> list() {
        return timeEntryRepository.findAll();
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        boolean teExists = timeEntryRepository.existsById(id);
        if (teExists) {
            timeEntry.setId(id);
            return timeEntryRepository.save(timeEntry);
        }
        return null;
    }


    public void delete(Long id) {
        timeEntryRepository.deleteById(id);
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

