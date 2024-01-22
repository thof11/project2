package com.jira.demo.controller;

import com.jira.demo.client.AddressFeignClient;
import com.jira.demo.dto.EmployeeSquadDto;
import com.jira.demo.dto.EmployeeTaskDto;
import com.jira.demo.model.Employee;
import com.jira.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final AddressFeignClient addressServiceFeignClient;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

//    Either this @AllArgsConstructor or this
//    public EmployeeController(AddressFeignClient addressServiceFeignClient,
//                              EmployeeService employeeService,
//                              ModelMapper modelMapper) {
//        this.addressServiceFeignClient = addressServiceFeignClient;
//        this.employeeService = employeeService;
//        this.modelMapper = modelMapper;
//    }

    @GetMapping("/employee-squad")
    public ResponseEntity<List<EmployeeSquadDto>> getAllEmployeeById() {
        List<EmployeeSquadDto> employeeSquadDto = employeeService
                .getAllEmployees()
                .stream()
                .map(squad -> modelMapper.map(squad, EmployeeSquadDto.class))
                .toList();
        return new ResponseEntity<>(employeeSquadDto, HttpStatus.OK);

    }

    @GetMapping("/employee-Task")
    public ResponseEntity<List<EmployeeTaskDto>> getAllEmployeeTaskById() {
        List<EmployeeTaskDto> employeeTaskDtos = employeeService.getAllEmployees()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeTaskDto.class))
                .toList();
        return new ResponseEntity<>(employeeTaskDtos, HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeCreateResponseDto> createEmployee(@Valid @RequestBody EmployeeCreateRequestDto employeeRequestDto) {
        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeUpdateResponseDto> updateEmployee(@PathVariable("id") long employeeId,
                                                                    @Valid @RequestBody EmployeeUpdateRequestDto employeeRequestDto) {
        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);
        Employee updateEmployee = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(modelMapper.map(updateEmployee, EmployeeUpdateResponseDto.class), HttpStatus.OK);
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

    @GetMapping("/employee-addresses")
    public ResponseEntity<String> getAddressesForEmployee() {
        return ResponseEntity.ok(addressServiceFeignClient.getAddressesFromServiceA());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}
