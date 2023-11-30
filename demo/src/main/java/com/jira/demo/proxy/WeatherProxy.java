package com.jira.demo.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "weather-proxy",
        url = "https://api.open-meteo.com"
)
public interface WeatherProxy {

    // post this url in your browser to see the actual response
    // https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41
    @GetMapping("/v1/forecast")
    Map<String, Object> getCurrentWeather(@RequestParam("latitude") String latitude,
                                          @RequestParam("longitude") String longitude);

}
