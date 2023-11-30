package com.jira.demo.utils;

import com.jira.demo.enums.Role;
import com.jira.demo.model.Employee;
import com.jira.demo.model.Task;
import com.jira.demo.repository.EmployeeRepo;
import com.jira.demo.repository.TaskRepo;

import java.security.AccessControlException;

public class TaskProxy {
    private final TaskRepo taskRepo;

    public void getTaskWithAccessControl(Employee currentEmployee) {
        // Check if the currentEmployee has permission to perform the action on the task
        // You can use currentEmployee's roles, permissions, or any authorization mechanism

        if (!currentEmployee.hasRole(Role.ADMIN)) {
            throw new AccessControlException("Unauthorised access to action");
        }

        //performTaskAction();
        String taskName = String.valueOf(currentEmployee.getTask());
        System.out.println(taskName);


    }

    public void modifyTaskWithAccessControl(Employee employee, Task task, String taskTitle) {
        // Check employee permissions for modifying a task
        if (!employee.hasRole(Role.ADMIN)) {
            throw new AccessControlException("Insuffiecient permission to modifiy task");
        }

        if (employee.getTask() == task) {
            task.setTaskTitle(taskTitle);
        } else {
            System.out.println("Employee does not have task");
        }


        // Logic to modify the task

    }

    public void deleteTaskWithAccessControl(Employee employee, Task task) {

        if (!employee.hasRole(Role.ADMIN)) {
            throw new AccessControlException("Insufficient permissions to delete task");
        }

        if (employee.getTask() == task) {

            taskRepo.delete(task);
        } else {
            System.out.println("Employee does not have task");
        }


        // Logic to delete the task0
    }
}
