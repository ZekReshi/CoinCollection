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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPreservation() {
        return preservation;
    }

    public void setPreservation(int preservation) {
        this.preservation = preservation;
    }

    public int getYearOfCoinage() {
        return yearOfCoinage;
    }

    public void setYearOfCoinage(int yearOfCoinage) {
        this.yearOfCoinage = yearOfCoinage;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

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
