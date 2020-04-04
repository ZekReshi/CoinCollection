package at.schwarczflorian.coincollection.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Collector extends PanacheEntity {

    private String firstName;
    private String lastName;

    //region constructors

    public Collector() { }

    public Collector(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //endregion

    //region Getter and Setter

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //endregion

}
