package at.schwarczflorian.model;

import java.awt.*;

public class Coin {

    private long id;
    private int value;
    private int preservation;
    private int yearOfCoinage;
    private Source source;
    private Currency currency;
    private Collector collector;

    //region Constructors

    public Coin() {
    }

    public Coin(long id, int value, int preservation, int yearOfCoinage, Source source, Currency currency, Collector collector) {
        this.id = id;
        this.value = value;
        this.preservation = preservation;
        this.yearOfCoinage = yearOfCoinage;
        this.source = source;
        this.currency = currency;
        this.collector = collector;
    }

    //endregion

    //region Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    //endregion

}
