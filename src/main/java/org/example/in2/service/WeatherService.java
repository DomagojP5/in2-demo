package org.example.in2.service;

import org.example.in2.model.WeatherApiResponse;
import org.example.in2.model.WeatherModel;
import org.example.in2.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }


    public List<WeatherModel> findAll() {
        return weatherRepository.findAll();
    }

    public void save(WeatherModel weatherModel) {
        weatherRepository.save(weatherModel);
    }

    public void fetchAndSaveWeather(String city) {

        WeatherModel model = weatherRepository.findByCityName(city).orElse(new WeatherModel());

        try {
            //API call
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=" + apiKey + "&units=metric";

            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            //convert to model
            model.setCityName(city);
            model.setTemperature(response.getMain().getTemp());
            model.setFetchedAt(LocalDateTime.now());
            model.setStatus(WeatherModel.Status.SUCCESS);

            save(model);

        } catch (Exception e) {
            //handle error

            model.setCityName(city);
            model.setFetchedAt(LocalDateTime.now());
            model.setStatus(WeatherModel.Status.FAILURE);

            save(model);

            //throw new RuntimeException(e);
            System.err.println(e.getMessage());
        }
    }

    public void updateAll() {
        List<WeatherModel> existing = weatherRepository.findAll();
        for (WeatherModel model : existing) {
            fetchAndSaveWeather(model.getCityName());
        }
    }
}
