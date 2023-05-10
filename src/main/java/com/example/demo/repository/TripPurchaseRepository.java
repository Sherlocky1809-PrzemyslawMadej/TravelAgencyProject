package com.example.demo.repository;

import com.example.demo.model.TripPurchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripPurchaseRepository extends JpaRepository<TripPurchase, Long> {

    @Query(value = "SELECT * FROM trip_purchase JOIN trip ON trip.trip_id = trip_purchase.purchased_trip_trip_id " +
            "WHERE trip.trip_id = ?1 LIMIT 5", nativeQuery = true)
    List<TripPurchase> findAllPurchasesByGivenTrip(Long tripId);
//
//    List<TripPurchase> findTop5();

    Optional<TripPurchase> findByPurchaseId(Long purchaseId);

}
