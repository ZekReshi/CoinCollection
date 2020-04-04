package at.schwarczflorian.coincollection.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Collector extends PanacheEntity {

    private String firstName;
    private String lastName;

    public Collector() { }

}
