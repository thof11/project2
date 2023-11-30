package com.jira.demo.controller;

import com.jira.demo.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getWeather() {
        return ResponseEntity.ok(weatherService.getInfoAboutWeather("52.52", "13.41"));
    }

}
