package org.example.in2.repository;

import org.example.in2.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherModel, Long>    {

    Optional<WeatherModel> findByCityName(String city);
}
