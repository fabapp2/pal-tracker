package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PalTrackerApplication implements CommandLineRunner {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        Long projectId = 1L,
        Long userId = 2L;
        LocalDate date LocalDate.now();
        int hours = 8;

        TimeEntry timeEntry = new TimeEntry(projectId, userId, date, hours);
        timeEntryRepository.create(timeEntry);
    }
}
