package com.example.demo.repository;

import com.example.demo.dto.ContinentDTO;
import com.example.demo.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Byte> {

    Optional<Continent> findByContinentName(String continentName);
}
