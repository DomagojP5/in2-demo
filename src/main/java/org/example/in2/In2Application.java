package org.example.in2;

import jakarta.annotation.PostConstruct;
import org.example.in2.model.WeatherModel;
import org.example.in2.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class In2Application {

    public static void main(String[] args) {
        SpringApplication.run(In2Application.class, args);
    }

    @Bean
    CommandLineRunner init(WeatherService weatherService) {
        return args -> {
            // Add initial cities
            weatherService.save(new WeatherModel("Zagreb", null, null, WeatherModel.Status.PENDING));
            weatherService.save(new WeatherModel("London", null, null, WeatherModel.Status.PENDING));
            weatherService.save(new WeatherModel("New York", null, null, WeatherModel.Status.PENDING));
            weatherService.save(new WeatherModel("Tokyo", null, null, WeatherModel.Status.PENDING));
        };
    }

}
