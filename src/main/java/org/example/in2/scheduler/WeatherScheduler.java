package org.example.in2.scheduler;

import org.example.in2.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    private final WeatherService weatherService;

    public WeatherScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 60000) //svakih 60s
    public void scheduledTask() {
        System.out.println("Scheduler running...");
        weatherService.updateAll();
    }
}
