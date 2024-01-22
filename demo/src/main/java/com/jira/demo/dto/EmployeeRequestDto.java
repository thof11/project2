package com.jira.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class EmployeeRequestDto {

    @Valid

    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 20, message = "number of characters must be between 0 and 20")
    private String name;

    @NotNull(message = "name is mandatory")
    @NotBlank(message = "name is mandatory")
    private String email;

    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 16, max = 32, message = "number of characters must be between 16 and 32")
    private String password;

    @NotNull(message = "name is mandatory")
    @Positive
    @Min(0)
    @Max(170)
    //@Size(min= 0, max=200, message="Age cannot be below 0 or over 200")
    private int age;

}
