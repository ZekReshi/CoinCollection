package at.schwarczflorian.coincollection.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Source extends PanacheEntity {

    private String name;

    //region Constructors

    public Source() { }

    public Source(String name) {
        this.name = name;
    }

    //endregion

    //region Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion
    
}
