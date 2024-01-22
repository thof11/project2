package com.jira.demo.service;


import com.jira.demo.exception.NotFoundException;
import com.jira.demo.model.Employee;
import com.jira.demo.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + employeeId));

    }

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(long employeeId, Employee employee) {
        Employee existingEmployee = getEmployeeById(employeeId);
        existingEmployee.setName(employee.getName());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());
        return employeeRepo.save(existingEmployee);
    }

    public void deleteEmployee(long employeeId) {
        employeeRepo.deleteById(employeeId);
    }

    public void deleteAllEmployees() {
        employeeRepo.deleteAll();
    }
}

