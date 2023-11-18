package com.jira.demo.config;

import com.jira.demo.converter.EmployeeInboundConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.valueOf("PRIVATE"));
        modelMapper.addConverter(new EmployeeInboundConverter());
        return new ModelMapper();
    }


}
