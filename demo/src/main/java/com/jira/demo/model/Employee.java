package com.jira.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jira.demo.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String Occupation;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "employee_roles")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @ManyToOne // pass arguments to that annotation
    @JoinColumn(name = "squad_id")
    @JsonIgnore
    private Squad squad;

    // not sure about all,
    @OneToOne(mappedBy = "employee", cascade = CascadeType.DETACH) // Consider using mappedBy
    @JsonIgnore
    private Task task;


    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

}
