package entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String jobtitle;
    private int mgr;
    private LocalDate hireDate;
    private double salary;
    private double comm;
    private Department department;

    public Employee() {
    }

    public Employee(int id, String name, String jobtitle, int mgr, LocalDate hireDate, double salary, double comm, Department department) {
        this.id = id;
        this.name = name;
        this.jobtitle = jobtitle;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.salary = salary;
        this.comm = comm;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setId() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof Employee){
            return ((Employee)obj).name.equals(name) &&
                    ((Employee)obj).hireDate.equals(hireDate) &&
                    ((Employee)obj).salary == salary &&
                    ((Employee)obj).jobtitle.equals(jobtitle);
        }
        return super.equals(obj);
    }
}
