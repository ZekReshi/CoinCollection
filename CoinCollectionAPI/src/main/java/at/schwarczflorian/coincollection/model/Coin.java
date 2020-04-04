package at.schwarczflorian.coincollection.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
public class Coin extends PanacheEntity {

    private int value;
    private int preservation;
    private int yearOfCoinage;
    @ManyToOne
    private Source source;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private Collector collector;
    @JsonbTransient
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] front;
    @JsonbTransient
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] back;

    public Coin() { }

    //region Getter and Setter

    public byte[] getFront() {
        return front;
    }

    public void setFront(byte[] front) {
        this.front = front;
    }

    public byte[] getBack() {
        return back;
    }

    public void setBack(byte[] back) {
        this.back = back;
    }

    //endregion

}
