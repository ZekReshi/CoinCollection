package at.schwarczflorian.model;

public class Coin {

    private long coin_id;
    private int value;
    private int preservation;
    private int yearOfCoinage;
    private Source source;
    private Currency currency;
    private Collector collector;

    //region Constructors

    public Coin() {
    }

    public Coin(long coin_id, int value, int preservation, int yearOfCoinage, Source source, Currency currency, Collector collector) {
        this.coin_id = coin_id;
        this.value = value;
        this.preservation = preservation;
        this.yearOfCoinage = yearOfCoinage;
        this.source = source;
        this.currency = currency;
        this.collector = collector;
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
