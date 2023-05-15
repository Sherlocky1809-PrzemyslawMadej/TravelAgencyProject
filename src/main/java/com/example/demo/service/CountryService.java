package com.example.demo.service;

import com.example.demo.dto.CountryDTO;
import com.example.demo.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CountryService {

    private final CountryRepository countryRepository;
    private final ConverterService converterService;
    private final ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository, ConverterService converterService, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.converterService = converterService;
        this.modelMapper = modelMapper;
    }

    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(converterService::convertCountryToDTO)
                .collect(Collectors.toList());
    }


}
