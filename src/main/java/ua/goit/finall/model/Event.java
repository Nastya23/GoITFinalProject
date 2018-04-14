package ua.goit.finall.model;

import javax.persistence.*;
import java.util.Date;

/**
 * The class implements of methods for working
 * with entities of the Event class.
 */
@Entity
@Table(name = "events")
public class Event extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private EventType type;

    @ManyToOne
    @JoinColumn(name = "CREATED_USER_ID")
    private User createdByUser;

    @Column(name = "EVENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    @Column(name = "HOURS")
    private Integer hours;

    public Event(EventType type, User createdByUser, Date eventDate, Integer hours) {
        this.type = type;
        this.createdByUser = createdByUser;
        this.eventDate = eventDate;
        this.hours = hours;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(super.getId());
        sb.append(", type=").append(type);
        sb.append(", createdByUser=").append(createdByUser);
        sb.append(", eventDate=").append(eventDate);
        sb.append(", hours=").append(hours);
        sb.append('}');
        return sb.toString();
    }

}
