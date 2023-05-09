package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT * FROM hotel JOIN city ON city.city_id = hotel.city_city_id" +
            " WHERE (?1 is null OR LOWER(hotel_name) LIKE %?1%) AND (?2 is null OR LOWER(city.city_name) LIKE %?2%)", nativeQuery = true)
    List<Hotel> findAllByParameters(String hotelName, String cityName);
}
