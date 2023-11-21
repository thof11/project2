package com.jira.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long squadId;

    // Add more fields here: name, description and etc
    @Column
    private String squadName;

    @Column
    private String description;

    @Column
    private String squadLeader;

    // not sure about all
    @OneToMany(mappedBy = "squad", cascade = CascadeType.DETACH, orphanRemoval = false)
    private List<Employee> employees;


    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
        employee.setSquad(this);
    }

    public void removeEmployee(Employee employee) {
        if (employee != null) {
            employees.remove(employee);
            employee.setSquad(null);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Squad squad) {
            return Objects.equals(squad.getSquadId(), this.squadId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.squadId);
    }
}
