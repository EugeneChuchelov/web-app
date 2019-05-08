package entity;

public class Address {
    private Integer id;

    private String value;

    private Employee employee;

    public Address() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ").append(id).append("; ");
        stringBuilder.append("value = ").append(value).append(";");
        return stringBuilder.toString();
    }
}
