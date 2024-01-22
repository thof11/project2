package com.jira.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class EmployeeDto {

    @Valid

    @NotNull(message="name is mandatory")
    @NotBlank(message="name is mandatory")
    @Size(min= 2, max=20, message="number of characters must be between 0 or 20")
    private String name;

    @NotNull(message="name is mandatory")
    @NotBlank(message="name is mandatory")
    private String email;

    @NotNull(message="name is mandatory")
    @Positive
    @Min(0)
    @Max(170)
    //@Size(min= 0, max=200, message="Age cannot be below 0 or over 200")
    private int age;

}
