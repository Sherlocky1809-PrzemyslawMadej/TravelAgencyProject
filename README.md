# TravelAgencyProject

This is a REST API service for travel agency, which operates database of trips from more than 60 countries around the world.
The app is written by Spring boot framework v. 3.0.6. It consist of such facilities as:
-> Display promoted trips;
-> Display trips filtered by continent and/or country name;
-> Display upcoming trips in next 30 days and much more.

You can familiarize with all controllers below and in Swagger UI documentation under this URL: http://localhost:8081/travel-agency/swagger-ui/index.html

## 1. Trip configuration

This controller is a configuration page for Admin - the only one who has access to this controller via URL: http://localhost:8081/travel-agency/trip-configuration.
This URL is secured by basic authentication in **Spring security**. The controller has two endpoints:

-> **POST /add-trip** which add validated Trip to repository.
@Parameter is required and this is @RequestBody Trip.

-> **PUT /edit-trip** which edit validated Trip in repository.
@Parameter is required and this is @RequestBody Trip.

## 2. Main
This controller operates trip management - it enables display collection of trip saved in database by parameter/s given.
This controller is available via URL: http://localhost:8081/travel-agency/main. The controller has four endpoints:

-> **GET /trips-promoted** which return set of trips which is switched as promoted. No parameter to insert.
