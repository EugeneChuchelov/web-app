package entity;

import java.util.Date;

public class Employee {
    private Integer id;

    private String name;

    private String job;

    private Integer manager;

    private Date hireDate;

    private Double salary;

    private Double comm;

    private Department department;

    private Address address;

    public Employee() {}

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ").append(id).append("; ");
        stringBuilder.append("name = ").append(name).append("; ");
        stringBuilder.append("job = ").append(job).append("; ");
        stringBuilder.append("manager = ").append(manager).append("; ");
        stringBuilder.append("hiredate = ").append(hireDate.toString()).append("; ");
        stringBuilder.append("salary = ").append(salary).append("; ");
        stringBuilder.append("comm = ").append(comm).append("; ");
        if(department != null){
            stringBuilder.append("department = ").append(department).append("; ");
        }
        if(address != null){
            stringBuilder.append("address = ").append(address).append(";");
        }

        return stringBuilder.toString();
    }
}
