package com.jira.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @ManyToOne // pass arguments to that annotation
    @JoinColumn(name = "squad_id")
    @JsonIgnore
    private Squad squad;

    // not sure about all,
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL) // Consider using mappedBy
    @JsonIgnore
    private Task task;
}
