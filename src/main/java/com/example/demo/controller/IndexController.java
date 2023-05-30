package com.example.demo.controller;

import com.example.demo.dto.TripDTO;
import com.example.demo.service.EntityService;
import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/mainPage")
public class IndexController {

    private final EntityService entityService;
    private final TripService tripService;

    public IndexController(EntityService entityService, TripService tripService) {
        this.entityService = entityService;
        this.tripService = tripService;
    }

    @GetMapping("/continentsAndCountries")
    public String getContinentList(ModelMap modelMap) {

        log.info("Lista kontynentów: ");

        List<String> continentNameList = entityService.getContinentNameList();
        modelMap.addAttribute("continentNames", continentNameList);

        log.info("Lista krajów na podstawie wybranego kontynentu: ");

        List<String> countryNameList = entityService.getCountryNameList();
        modelMap.addAttribute("countryNames", countryNameList);

        return "index";
    }

    @GetMapping("/tripsPromoted")
    public String getTripsPromoted(ModelMap modelMap) {

        log.info("Wycieczki promowane: ");

        Set<TripDTO> setOfPromotedTrips = tripService.getTripsPromoted();
        modelMap.addAttribute("promotedTrips", setOfPromotedTrips);

        return "index";
    }

    @GetMapping("/continentCountryTrips")
    public String getUpcomingTripsByContinentAndCountry(
            ModelMap modelMap,
            @RequestParam(value = "continent-name") String continentName,
            @RequestParam(value = "country-name", required = false) String countryName
    ) {

        log.info("Wybrane wycieczki na podstawie danego kontynentu lub kraju:");

        List<TripDTO> listOfTrips = tripService.getUpcomingTripsByContinentNameOrCountryName(continentName, countryName);
        modelMap.addAttribute("upcomingTrips", listOfTrips);

        return "destination";
    }

    @GetMapping("/lastPurchased")
    public String getLastPurchasedTrips(ModelMap modelMap) {

        log.info("Wycieczki ostatnio zakupione: ");

        List<TripDTO> listOfTripsLastPurchased = tripService.getTripsLastPurchased();
        modelMap.addAttribute("lastPurchasedTrips", listOfTripsLastPurchased);

        return "index";
    }

}
