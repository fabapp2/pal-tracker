package io.pivotal.pal.tracker;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "time_entries")
public class TimeEntry {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // Long can be null and represent an unset pk whereas long would be 0 if not set which is a vaklid PK
    private Long projectId;
    private Long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {
    }

    public TimeEntry(Long projectId, Long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(Long id, Long projectId, Long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return hours == timeEntry.hours &&
                Objects.equals(id, timeEntry.id) &&
                Objects.equals(projectId, timeEntry.projectId) &&
                Objects.equals(userId, timeEntry.userId) &&
                Objects.equals(date, timeEntry.date);
    }

    /**
     * using the id generated bz JPA in the equals() method might get you into problem as the field is not immutable anzymore then!
     */
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        TimeEntry timeEntry = (TimeEntry) o;
//
//        if (id != timeEntry.id) return false;
//        if (projectId != timeEntry.projectId) return false;
//        if (! userId.equals(timeEntry.userId)) return false;
//        if (hours != timeEntry.hours) return false;
//        return date != null ? date.equals(timeEntry.date) : timeEntry.date == null;
//    }



    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + hours;
        return result;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
            "id=" + id +
            ", projectId=" + projectId +
            ", userId=" + userId +
            ", date='" + date + '\'' +
            ", hours=" + hours +
            '}';
    }
}
