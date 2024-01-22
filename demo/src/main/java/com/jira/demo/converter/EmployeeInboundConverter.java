package com.jira.demo.converter;


import com.jira.demo.dto.EmployeeRequestDto;
import com.jira.demo.model.Employee;
import org.modelmapper.AbstractConverter;

public class EmployeeInboundConverter extends AbstractConverter<Employee, EmployeeRequestDto> {

    @Override
    protected EmployeeRequestDto convert(Employee employee) {
        EmployeeRequestDto employeeRequestDTO = new EmployeeRequestDto();
        employeeRequestDTO.setName(employee.getName());
        employeeRequestDTO.setAge(employee.getAge());
        return employeeRequestDTO;
    }

}
