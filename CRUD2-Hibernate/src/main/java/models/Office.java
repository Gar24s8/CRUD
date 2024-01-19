package models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "office_name")
    private String officeName;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    public Office() {
    }

    public Office(String officeName, String address) {
        this.officeName = officeName;
        this.address = address;
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employee.setOffice(this);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public int getId() {
        return id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", officeName='" + officeName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

