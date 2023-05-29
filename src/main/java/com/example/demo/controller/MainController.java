package com.example.demo.controller;

import com.example.demo.dto.TripDTO;
import com.example.demo.service.EntityService;
import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/main-page")
public class MainController {

    private final EntityService entityService;
    private final TripService tripService;

    public MainController(EntityService entityService, TripService tripService) {
        this.entityService = entityService;
        this.tripService = tripService;
    }

    @GetMapping("/continents")
    public List<String> getContinentList() {
        log.info("Lista kontynentów: ");
        return entityService.getContinentNameList();
    }

    @GetMapping("/countries")
    public List<String> getCountryListByContinent(
            @RequestParam(value = "continent") String continentName
    ) {
        log.info("Lista krajów na podstawie wybranego kontynentu: ");
        return entityService.getCountryNameListByContinent(continentName);
    }

    @GetMapping("/trips-promoted")
    public Set<TripDTO> getTripsPromoted() {

        log.info("Wycieczki promowane: ");

        return tripService.getTripsPromoted();
    }
}
