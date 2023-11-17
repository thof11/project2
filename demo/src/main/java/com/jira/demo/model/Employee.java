package com.jira.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Employees")




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

  //  @Column
   // private long TaskId;

    @ManyToOne // pass arguments to that annotation
    @JoinColumn(name = "squad_id")
    @JsonIgnore
    private Squad squad;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL) // Consider using mappedBy
    @JsonIgnore
    private Task task;



}
