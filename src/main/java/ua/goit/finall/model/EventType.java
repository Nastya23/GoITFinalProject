package ua.goit.finall.model;

import javax.persistence.*;

/**
 * The class implements of methods for working
 * with entities of the EventType class.
 */
@Entity
@Table(name = "event_types")
public class EventType extends BaseEntity {
    public enum Types {
        WORK("Working day"), TRAINING("Technical training");
        String name;

        Types(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Column(name = "TYPE")
    private String type;

    @Column(name = "RATE")
    private Double rate;

    public EventType() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventType{");
        sb.append("id=").append(super.getId());
        sb.append(", type='").append(type).append('\'');
        sb.append(", rate=").append(rate);
        sb.append('}');
        return sb.toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }


}
