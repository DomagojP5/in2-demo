package org.example.in2.controller;

import org.example.in2.model.WeatherModel;
import org.example.in2.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WeatherWebController {

    private final WeatherService weatherService;

    public WeatherWebController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String getWeatherPage(Model model) {
        model.addAttribute("weathers", weatherService.findAll());
        return "weather";
    }
}
