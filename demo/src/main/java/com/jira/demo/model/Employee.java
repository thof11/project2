package com.jira.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jira.demo.enums.Role;
import jakarta.persistence.*;
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

    @ElementCollection(targetClass= Role.class)
    @CollectionTable(name= "employee_roles", joinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>;

    public Set<Role> getRoles(){
        return roles;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    @ManyToOne // pass arguments to that annotation
    @JoinColumn(name = "squad_id")
    @JsonIgnore
    private Squad squad;

    // not sure about all,
    @OneToOne(mappedBy = "employee", cascade = CascadeType.DETACH, orphanRemoval = false) // Consider using mappedBy
    @JsonIgnore
    private Task task;
}
