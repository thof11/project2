package com.jira.demo.service;


import com.jira.demo.NotFoundException;
import com.jira.demo.dto.EmployeeNameDTO;
import com.jira.demo.dto.EmployeeSquadDTO;

//import com.jira.demo.exception.NotFoundException;
import com.jira.demo.dto.EmployeeTaskDTO;
import com.jira.demo.model.Employee;
import com.jira.demo.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {


    private final EmployeeRepo employeeRepo;

    @Autowired
    private final ModelMapper modelMapper;

    public List<EmployeeSquadDTO>getAllEmployeesSquadDTOs() {
        List<Employee> employees= employeeRepo.findAll();
        //ModelMapper
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeSquadDTO.class))
                .collect(Collectors.toList());

    }
    public List<EmployeeTaskDTO>getAllEmployeesTaskDTOs() {
        List<Employee> employees= employeeRepo.findAll();
        //ModelMapper
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeTaskDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeNameDTO convertToDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeNameDTO.class);
    }

   /* public List<EmployeeSquadDTO>getAllEmployeesSquadDTOs(){
        return employeeRepo.findAll()
                .stream()
                .map(this:: convertEntityToDTO)
                .collect(Collectors.toList());

    }

    private EmployeeSquadDTO convertEntityToDTO(Employee employee){
        EmployeeSquadDTO employeeSquadDTO= new EmployeeSquadDTO();
        employeeSquadDTO.setName(employee.getName());
        employeeSquadDTO.setEmail(employee.getEmail());
        if (employee.getSquad() != null){
        employeeSquadDTO.setSquadName(employee.getSquad().getSquadName());}
        return employeeSquadDTO;
    }*/


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

