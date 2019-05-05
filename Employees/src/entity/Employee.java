package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "EMP")
public class Employee implements Serializable {
    @Id
    @Column(name = "EMPNO")
    private Integer id;
    @Column(name = "ENAME")
    private String name;
    @Column(name = "JOB")
    private String jobtitle;
    @Column(name = "MGR")
    private Integer mgr;
    @Column(name = "HIREDATE")
    private LocalDate hireDate;
    @Column(name = "SAL")
    private Double salary;
    @Column(name = "COMM")
    private Double comm;
    @ManyToOne
    @JoinColumn(name = "DEPTNO")
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
