package at.schwarczflorian.model;

public class Currency {

    private long id;
    private String name;

    //region Constructors

    public Currency() {
    }

    public Currency(long id, String name) {
        this.id = id;
        this.name = name;
    }

    //endregion

    //region Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion

}
