package at.schwarczflorian.model;

public class Source {

    private long source_id;
    private String name;

    //region Constructors

    public Source() {
    }

    public Source(long source_id, String name) {
        this.source_id = source_id;
        this.name = name;
    }

    //endregion

    //region Getter and Setter

    public long getSource_id() {
        return source_id;
    }

    public void setSource_id(long source_id) {
        this.source_id = source_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //endregion

}
