package com.jira.demo.converter;


import com.jira.demo.dto.EmployeeDto;
import com.jira.demo.model.Employee;
import org.modelmapper.AbstractConverter;

public class EmployeeInboundConverter extends AbstractConverter<Employee, EmployeeDto> {

    @Override
    protected EmployeeDto convert(Employee employee) {
        EmployeeDto employeeDTO = new EmployeeDto();
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        return employeeDTO;
    }

}
