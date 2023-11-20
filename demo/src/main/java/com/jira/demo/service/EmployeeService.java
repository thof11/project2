package com.jira.demo.service;


import com.jira.demo.exception.NotFoundException;
import com.jira.demo.dto.EmployeeSquadDto;
import com.jira.demo.dto.EmployeeTaskDto;
import com.jira.demo.model.Employee;
import com.jira.demo.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    public List<EmployeeSquadDto> getAllEmployeesSquadDTOs() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeSquadDto.class))
                .collect(Collectors.toList());

    }

    public List<EmployeeTaskDto> getAllEmployeesTaskDTOs() {
        List<Employee> employees = employeeRepo.findAll();
        //ModelMapper
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeTaskDto.class))
                .collect(Collectors.toList());

    }

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
        existingEmployee.setOccupation(employee.getOccupation());
        return employeeRepo.save(existingEmployee);
    }

    public void deleteEmployee(long employeeId) {
        employeeRepo.deleteById(employeeId);
    }

    public void deleteAllEmployees() {
        employeeRepo.deleteAll();
    }
}

