package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM client WHERE (?1 is null OR LOWER(first_name) LIKE %?1%)" +
            " AND (?2 is null OR LOWER(last_name) LIKE %?2%) AND (?3 is null OR age = ?3)" +
            " AND (?4 is null OR LOWER(email) LIKE %?4%) AND (?5 is null OR pesel LIKE %?5%)" +
            " AND (?6 is null OR LOWER(number_of_id_card) LIKE %?6%) LIMIT ?7", nativeQuery = true)
    List<Client> findAllByParameters(String firstName, String lastName, Short age, String email, String pesel,
                                     String numberOfIdCard, int numberOfRecords);

    Optional<Client> findByClientId(Long clientId);
}
