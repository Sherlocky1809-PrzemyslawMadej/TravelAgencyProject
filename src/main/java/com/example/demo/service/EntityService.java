package com.example.demo.service;

import com.example.demo.model.Continent;
import com.example.demo.model.Country;
import com.example.demo.repository.ContinentRepository;
import com.example.demo.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EntityService {

    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;

    public EntityService(ContinentRepository continentRepository, CountryRepository countryRepository) {
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
    }

    public List<String> getContinentNameList() {

        List<String> continentNameList = new ArrayList<>();

        for (Continent continent: continentRepository.findAll()) {
            continentNameList.add(continent.getContinentName());
        }
        return continentNameList;
    }

    public List<String> getCountryNameListByContinent(String continentName) {

        List<String> countryNameList = new ArrayList<>();

        for (Country country: countryRepository.findAll()) {
            if(country.getContinent().getContinentName().equals(continentName)) {
                countryNameList.add(country.getCountryName());
            }
        }
        return countryNameList;
    }
}
