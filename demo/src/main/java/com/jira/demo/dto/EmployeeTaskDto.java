package com.jira.demo.dto;

import lombok.Data;

@Data

public class EmployeeTaskDto {
    private String name;
    private String email;
    private String squadName;

    private String taskTitle;
    private int storyPoints;
}
