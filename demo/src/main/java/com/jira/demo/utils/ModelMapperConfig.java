package com.jira.demo.utils;

import com.jira.demo.adapters.EmployeeNameConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.valueOf("PRIVATE"));
        modelMapper.addConverter(new EmployeeNameConverter());
        return new ModelMapper();
    }


}
