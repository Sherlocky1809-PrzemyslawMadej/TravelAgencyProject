package com.example.demo.repository;

import com.example.demo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value = "SELECT * FROM city JOIN country ON city.city_id = country.country_id " +
            "WHERE (?1 is null OR LOWER(city_name) LIKE %?1%) AND (?2 is null OR LOWER(country.country_name) LIKE %?2%)",
            nativeQuery = true)
    List<City> findAllByParameters(String cityName, String countryName);

}
