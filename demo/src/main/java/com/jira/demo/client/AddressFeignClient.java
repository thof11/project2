package com.jira.demo.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient( name="address-service", url ="http://localhost:8050")
public interface AddressFeignClient {

    @GetMapping("addresses") // Endpoint to fetch addresses from other Microservice
    String getAddressesFromServiceA();
}
