package at.schwarczflorian.model;

public class Coin {

    private long coin_id;
    private int value;
    private int preservation;
    private int yearOfCoinage;
    private long source_id;
    private long currency_id;

    //region Constructors

    public Coin() {
    }

    public Coin(long coin_id, int value, int preservation, int yearOfCoinage, long source_id, long currency_id) {
        this.coin_id = coin_id;
        this.value = value;
        this.preservation = preservation;
        this.yearOfCoinage = yearOfCoinage;
        this.source_id = source_id;
        this.currency_id = currency_id;
    }

    //endregion

    //region Getter and Setter

    public long getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(long coin_id) {
        this.coin_id = coin_id;
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

    public long getSource_id() {
        return source_id;
    }

    public void setSource_id(long source_id) {
        this.source_id = source_id;
    }

    public long getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(long currency_id) {
        this.currency_id = currency_id;
    }

    //endregion

}
