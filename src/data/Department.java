package data;

import java.util.Set;

public class Department {
    private int id;
    private String name;
    private String description;
    private Set<Employee> employees;

    public Department() {
    }

    public Department(String name, String description, Set<Employee> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    protected Department(String name, String description, Set<Employee> employees, int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public Employee removeEmployee(Employee employee){
        employees.remove(employee);
        return employee;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setId() {

    }

    public int getId() {
        return id;
    }
}
