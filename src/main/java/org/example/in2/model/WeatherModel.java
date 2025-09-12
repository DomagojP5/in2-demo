package org.example.in2.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class WeatherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cityName;
    private Double temperature;
    private LocalDateTime fetchedAt;
    private LocalDateTime localTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    public WeatherModel() {

    }

    public WeatherModel(String cityName, Double temperature, LocalDateTime fetchedAt, LocalDateTime localTime, Status status) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.fetchedAt = fetchedAt;
        this.localTime = localTime;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        SUCCESS,
        FAILURE,
        PENDING
    }
}
