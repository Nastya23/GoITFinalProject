package ua.goit.finall.model;

import javax.persistence.*;

/**
 * The class implements of methods for working
 * with entities of the Status class.
 */
@Entity
@Table(name = "statuses")
public class Status extends BaseEntity{

    @Column(name = "TYPE")
    private String type;

    public Status() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Status{");
        sb.append("id=").append(super.getId());
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
