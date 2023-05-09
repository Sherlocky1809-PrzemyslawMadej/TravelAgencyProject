package com.example.demo.repository;

import com.example.demo.model.TripPurchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripPurchaseRepository {

    @Query(value = "SELECT * FROM trip_purchase JOIN trip ON trip.trip_id = trip_purchase.purchased_trip_trip_id " +
            "WHERE trip.trip_id = ?1 LIMIT 5", nativeQuery = true)
    List<TripPurchase> findAllPurchasesByGivenTrip(Long tripId);

    List<TripPurchase> findTop5ByDateOfPurchase(Sort sort);

}
