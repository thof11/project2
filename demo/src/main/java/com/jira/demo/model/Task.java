package com.jira.demo.model;


import com.jira.demo.utils.TaskProxy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Task")
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

    @OneToOne  // Consider changing this to @ManyToOne
    @JoinColumn(name = "Employee_id")
    private Employee employee;

    @Transient
    private TaskProxy proxy;

    public Task() {
        this.proxy = new TaskProxy();

    }

    public void doSomething(Employee currentEmployee){
        proxy.getTaskWithAccessControl(currentEmployee;
    }

    public void modifyTask(Employee employee, Task task,String taskTitle) {
        proxy.modifyTaskWithAccessControl(employee, task, taskTitle);

    }

    public void deleteTask(Employee employee, Task task) {
        proxy.deleteTaskWithAccessControl(employee, task);
    }




}




