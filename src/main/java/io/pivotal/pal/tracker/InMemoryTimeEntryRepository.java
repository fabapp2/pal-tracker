package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTimeEntryRepository extends SimpleJpaRepository<TimeEntry, Long> implements TimeEntryRepository {

    private final EntityManager entityManager;
//    @Autowired
//    private TimeEntryJpaRepository this;

/*
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();

    private long currentId = 1L;
*/
 public InMemoryTimeEntryRepository(JpaEntityInformation<TimeEntry, ?>
                                            entityInformation, EntityManager entityManager) {
     super(entityInformation, entityManager);
     this.entityManager = entityManager;
 }


    @Override
    public TimeEntry create(TimeEntry timeEntry) {
//        Long id = currentId++;
//
//        TimeEntry newTimeEntry = new TimeEntry(
//            id,
//            timeEntry.getProjectId(),
//            timeEntry.getUserId(),
//            timeEntry.getDate(),
//            timeEntry.getHours()
//        );
//
//        timeEntries.put(id, newTimeEntry);
        return this.save(timeEntry);
    }

    @Override
    public TimeEntry find(Long id) {
//        return timeEntries.get(id);
        Optional<TimeEntry> byId = this.findById(id);
        return byId.orElse(null);
    }

    @Override
    public List<TimeEntry> list() {
//        return new ArrayList<>(timeEntries.values());
        return this.findAll();
    }


    // Question: why do we need to pass the id and do not take iot from the model ?
    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
//        if (find(id) == null) return null;
        boolean teExists = this.existsById(id);
        if(teExists) {
            // TODO: setting the id should be sufficient ?
            TimeEntry timeEntryCloned = createClone(id, timeEntry);
            return this.save(timeEntryCloned);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
//        timeEntries.remove(id);
        this.deleteById(id);
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

