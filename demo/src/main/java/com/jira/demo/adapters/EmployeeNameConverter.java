package com.jira.demo.adapters;


import com.jira.demo.dto.EmployeeNameDTO;
import com.jira.demo.model.Employee;
import com.jira.demo.model.Task;
import org.modelmapper.AbstractConverter;

public class EmployeeNameConverter extends AbstractConverter<Employee, EmployeeNameDTO> {

    @Override
    protected EmployeeNameDTO convert (Employee source) {
        EmployeeNameDTO employeeDTO = new EmployeeNameDTO();
        employeeDTO.setName(source.getName());
        employeeDTO.setAge(source.getAge());

        return employeeDTO;
    }

    private  String formatName(String name){
        if(name ==null || name.isEmpty()){
            return "Unassigned";
        }



        String[] parts = name.split(" ");
        StringBuilder formattedName = new StringBuilder();
        for (String part : parts) {
            if (part.length() > 0) {

                formattedName.append(part.substring(0,1).toUpperCase())
                        .append(part.substring(1).toLowerCase())
                        .append("");

            }
        }

        return formattedName.toString().trim();




    }
}
