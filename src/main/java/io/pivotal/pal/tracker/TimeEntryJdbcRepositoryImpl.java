package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class TimeEntryJdbcRepositoryImpl implements
        TimeEntryRepository /*extends SimpleJpaRepository<TimeEntry, Long>*/ {

//    private final EntityManager entityManager;
//    @Autowired
//    private TimeEntryJdbcRepository timeEntryRepository;


    /*
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();

    private long currentId = 1L;
*/
// public TimeEntryRepositoryImpl(JpaEntityInformation<T, ?>
//                                            entityInformation, EntityManager entityManager) {
//     super(entityInformation, entityManager);
//     timeEntryRepository.entityManager = entityManager;
// }


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


        return null; //timeEntryRepository.save(timeEntry);
    }


    public TimeEntry find(Long id) {
//        return timeEntries.get(id);
        /*
        Optional<TimeEntry> byId = timeEntryRepository.findById(id);
        return byId.orElse(null);

         */
        return null;
    }


    public List<TimeEntry> list() {
//        return new ArrayList<>(timeEntries.values());
//        return timeEntryRepository.findAll();
        return null;
    }


    // Question: why do we need to pass the id and do not take iot from the model ?

    public TimeEntry update(Long id, TimeEntry timeEntry) {
//        if (find(id) == null) return null;
/*        boolean teExists = timeEntryRepository.existsById(id);
        if(teExists) {
            // TODO: setting the id should be sufficient ?
            TimeEntry timeEntryCloned = createClone(id, timeEntry);
            return timeEntryRepository.save(timeEntryCloned);
        }*/
        return null;
    }


    public void delete(Long id) {
//        timeEntries.remove(id);
//        timeEntryRepository.deleteById(id);
    }


    private TimeEntry createClone(Long id, TimeEntry timeEntry) {
//        TimeEntry updatedEntry = new TimeEntry(
//                id,
//                timeEntry.getProjectId(),
//                timeEntry.getUserId(),
//                timeEntry.getDate(),
//                timeEntry.getHours()
//        );
//        return updatedEntry;
        return null;
    }

}

