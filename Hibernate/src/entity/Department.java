package entity;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private Integer id;

    private String name;

    private String loc;

    private Set<Employee> employees = new HashSet<Employee>();

    public Department() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ").append(id).append("; ");
        stringBuilder.append("name = ").append(name).append("; ");
        stringBuilder.append("loc = ").append(loc).append(";");
        return stringBuilder.toString();
    }
}
