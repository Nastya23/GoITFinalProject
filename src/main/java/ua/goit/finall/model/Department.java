package ua.goit.finall.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * The class implements of methods for working
 * with entities of the Department class.
 */
@Entity
@Table(name = "departments")
public class Department extends BaseEntity{

    @Column(name = "NAME")
    @ApiModelProperty(notes = "The department name")
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("id=").append(super.getId());
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
