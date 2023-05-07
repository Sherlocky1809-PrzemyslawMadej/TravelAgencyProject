package com.example.demo.service;

import com.example.demo.dto.ContinentDTO;
import com.example.demo.model.Continent;
import com.example.demo.repository.ContinentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContinentService {

    private final ContinentRepository continentRepository;
    private final ModelMapper modelMapper;

    public ContinentService(ContinentRepository continentRepository, ModelMapper modelMapper) {
        this.continentRepository = continentRepository;
        this.modelMapper = modelMapper;
    }

    public ContinentDTO convertContinentToDTO(Continent continent) {
        return modelMapper.map(continent, ContinentDTO.class);
    }

    public List<ContinentDTO> getAllContinents() {
        return continentRepository.findAll().stream()
                .map(this::convertContinentToDTO)
                .collect(Collectors.toList());
    }
}
