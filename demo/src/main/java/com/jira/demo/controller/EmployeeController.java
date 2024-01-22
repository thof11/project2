package com.jira.demo.controller;

import com.jira.demo.client.AddressFeignClient;
import com.jira.demo.dto.EmployeeDto;
import com.jira.demo.dto.EmployeeSquadDto;
import com.jira.demo.dto.EmployeeTaskDto;
import com.jira.demo.model.Employee;
import com.jira.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@RequestMapping("/employees")
@AllArgsConstructor
@RestController
public class EmployeeController {

    private final AddressFeignClient addressServiceFeignClient;

    @Autowired
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

    @PostMapping("/employeeDto")
    public ResponseEntity<Employee> createEmployeeDto(@Valid @RequestBody EmployeeDto employeeDto){

        Employee createdEmployeeDto= employeeService.createEmployeeDto(employeeDto);

        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);

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

    @Autowired
    public EmployeeController(AddressFeignClient addressServiceFeignClient){
        this.addressServiceFeignClient= addressServiceFeignClient;
    }

    @GetMapping("/employee-addresses")
    public String getAddressesForEmployee() {

// Fetch addresses from other microservices using Feign client
        String addresses = addressServiceFeignClient.getAddressesFromServiceA();

        return addresses;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName=((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;

    }




}
