package com.example.demo.repository;

import com.example.demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Short> {

    @Query(value = "SELECT * FROM country JOIN continent ON continent.id = country.continent_id " +
            "WHERE (?1 is null OR LOWER(country_name) LIKE %?1%) AND (?2 is null OR LOWER(continent.continent_name) LIKE %?2%)",
    nativeQuery = true)
    List<Country> findAllByParameters(String countryName, String continentName);

    Optional<Country> findByCountryName(String countryName);
}
