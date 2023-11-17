package com.jira.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Task")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String taskTitle;

    @Column
    private String summary;

    @Column
    private int storyPoints;

    @OneToOne // Consider changing this to @ManyToOne
    @JoinColumn(name = "Employee_id")
    private Employee employee;
}



