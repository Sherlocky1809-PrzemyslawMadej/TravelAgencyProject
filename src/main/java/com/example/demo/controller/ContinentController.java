package com.example.demo.controller;

import com.example.demo.dto.ContinentDTO;
import com.example.demo.model.Continent;
import com.example.demo.service.ContinentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/continents")
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getContinents() {
        log.info("KONTYNENTY:");

        try {
            return ResponseEntity.ok(continentService.getAllContinents());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("INTERNAL SERVER ERROR :: " + exception.getMessage());
        }
    }
}
