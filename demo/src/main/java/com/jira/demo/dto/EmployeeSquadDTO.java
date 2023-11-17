package com.jira.demo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data

public class EmployeeSquadDTO {
    private String name;

    private String squadName;
    private String squadLeader;
}
