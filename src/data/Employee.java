package data;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String firstName;
    private String secondName;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String jobtitle;
    private double salary;
    private Department department;

    public Employee() {
    }

    public Employee(String firstName, String secondName, LocalDate birthDate,
                    LocalDate hireDate, String jobtitle, double salary, Department department) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.jobtitle = jobtitle;
        this.salary = salary;
        this.department = department;
    }

    protected Employee(int id, String firstName, String secondName, LocalDate birthDate,
                       LocalDate hireDate, String jobtitle, double salary, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.jobtitle = jobtitle;
        this.salary = salary;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
            return ((Employee)obj).firstName.equals(firstName) &&
                    ((Employee)obj).secondName.equals(secondName) &&
                    ((Employee)obj).birthDate.equals(birthDate) &&
                    ((Employee)obj).hireDate.equals(hireDate) &&
                    ((Employee)obj).salary == salary &&
                    ((Employee)obj).jobtitle.equals(jobtitle);
        }
        return super.equals(obj);
    }
}
