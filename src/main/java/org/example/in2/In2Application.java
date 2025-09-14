package org.example.in2;

import org.example.in2.model.WeatherModel;
import org.example.in2.service.WeatherService;
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
            //populacija baze
            String[] cities = {
                    "Zagreb", "London", "New York", "Tokyo", "Rio de Janeiro",
                    "Los Angeles", "Honolulu", "Wellington", "New Delhi", "Moscow"
            };

            for (String city : cities) {
                weatherService.save(new WeatherModel(city, null, null, null, WeatherModel.Status.PENDING));
            }

        };
    }

}
