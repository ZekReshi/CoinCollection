package at.schwarczflorian.coincollection.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Currency extends PanacheEntity {

    private String name;

    public Currency() { }

}
