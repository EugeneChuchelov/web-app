package entity;

public class Department {
    private int id;
    private String name;
    String loc;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    protected Department(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public Department(int id, String name, String loc) {
        this.id = id;
        this.name = name;
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setId() {

    }

    public int getId() {
        return id;
    }
}
