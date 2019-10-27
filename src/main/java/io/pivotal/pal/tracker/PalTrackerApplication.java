package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.jdbc.TimeEntryJdbcRepository;
import io.pivotal.pal.tracker.jpa.TimeEntryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EnableJpaRepositories(basePackages = "io.pivotal.pal.tracker.jpa")
@EnableJdbcRepositories(basePackages = "io.pivotal.pal.tracker.jdbc")
public class PalTrackerApplication /*implements CommandLineRunner */ {
    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class);
    }
}
