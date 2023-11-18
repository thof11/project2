package com.jira.demo.controller;

import com.jira.demo.dto.EmployeeSquadDto;
import com.jira.demo.dto.EmployeeTaskDto;
import com.jira.demo.model.Employee;
import com.jira.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/employees")
@AllArgsConstructor
@RestController
public class EmployeeController {


    private EmployeeService employeeService;

    @GetMapping("/employee-squad")
    public ResponseEntity<List<EmployeeSquadDto>> getAllEmployeeById() {
        List<EmployeeSquadDto> employeeDTOs = employeeService.getAllEmployeesSquadDTOs();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);

    }

    @GetMapping("/employee-Task")
    public ResponseEntity<List<EmployeeTaskDto>> getAllEmployeeTaskById() {
        List<EmployeeTaskDto> employeeDTOs = employeeService.getAllEmployeesTaskDTOs();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
                                                   @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployee() {
        employeeService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
