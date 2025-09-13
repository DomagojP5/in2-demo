package org.example.in2.service;

import org.example.in2.model.WeatherApiResponse;
import org.example.in2.model.WeatherModel;
import org.example.in2.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
        model.setCityName(city);

        try {
            //API call
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=" + apiKey + "&units=metric";

            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            assert response != null;
            updateModel(model, response);

        } catch (Exception e) {
            //handle error
            modelError(model, e);
        }

        save(model);
    }

    public void updateModel(WeatherModel model, WeatherApiResponse response) {

        if (response == null || response.getMain() == null) {
            modelError(model, new RuntimeException("Response is null"));
            return;
        }

        model.setTemperature(response.getMain().getTemp());
        model.setFetchedAt(LocalDateTime.now());

        LocalDateTime localTime = convertToLocalDateTime(response.getDt(), response.getTimezone());
        model.setLocalTime(localTime);

        model.setStatus(WeatherModel.Status.SUCCESS);
    }

    public void modelError(WeatherModel model, Exception e) {
        model.setTemperature(null);
        model.setFetchedAt(LocalDateTime.now());
        model.setLocalTime(null);
        model.setStatus(WeatherModel.Status.FAILURE);

        System.err.println(e.getMessage());
    }

    public LocalDateTime convertToLocalDateTime(long timestamp, int offset) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneOffset.ofTotalSeconds(offset));
    }

    public void updateAll() {
        List<WeatherModel> existing = weatherRepository.findAll();
        for (WeatherModel model : existing) {
            fetchAndSaveWeather(model.getCityName());
        }
    }
}
