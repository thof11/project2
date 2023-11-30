package com.jira.demo.service;

import com.jira.demo.proxy.WeatherProxy;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherProxy weatherProxy;

    public Map<String, Object> getInfoAboutWeather(String latitude, String longitude) {
        try {
            return weatherProxy.getCurrentWeather(latitude, longitude);
        } catch (FeignException feignException) {
            log.error("Error occurred while making Weather API call", feignException);
            throw new RuntimeException();
        }
    }
}
