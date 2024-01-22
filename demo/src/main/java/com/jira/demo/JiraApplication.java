package com.jira.demo;

import com.jira.demo.model.Employee;
import com.jira.demo.model.Squad;
import com.jira.demo.model.Task;
import com.jira.demo.repository.EmployeeRepo;
import com.jira.demo.repository.SquadRepo;
import com.jira.demo.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JiraApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private SquadRepo squadRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public void run(String... args) throws Exception {

        Squad squad = new Squad();
        squad.setSquadName("delta");
        squad.setSquadId(47L);
        squadRepo.save(squad);

        Employee employee1 = new Employee();
        employee1.setId(45L);
        employee1.setName("Barry");
        employee1.setEmail("hello@outlook.com");
        employee1.setSquad(squad);
        employeeRepo.save(employee1);


        Task task1 = new Task();
        task1.setId(31L);
        task1.setTaskTitle("Debug code");
        task1.setStoryPoints(5);
        task1.setEmployee(employee1);
        taskRepo.save(task1);
    }

    public static void main(String[] args) {

        SpringApplication.run(JiraApplication.class, args);
    }
}
