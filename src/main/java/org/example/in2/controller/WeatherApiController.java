package org.example.in2.controller;

import org.example.in2.model.WeatherModel;
import org.example.in2.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherApiController {

    private final WeatherService weatherService;

    public WeatherApiController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api")
    public List<WeatherModel> getWeather() {
        return weatherService.findAll();
    }
}
