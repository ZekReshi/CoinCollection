package at.schwarczflorian.model;

public class Currency {

    private long currency_id;
    private String name;

    //region Constructors

    public Currency() {
    }

    public Currency(long currency_id, String name) {
        this.currency_id = currency_id;
        this.name = name;
    }

    //endregion

    //region Getter and Setter

    public long getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(long currency_id) {
        this.currency_id = currency_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion

}
