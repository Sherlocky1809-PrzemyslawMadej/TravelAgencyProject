# TravelAgencyProject

This is a REST API service for travel agency, which operates database of trips from more than 60 countries around the world.
The app is written by Spring boot framework v. 3.0.6. It consist of such facilities as:
- Display promoted trips;
- Display trips filtered by continent and/or country name;
- Display upcoming trips in next 30 days and much more.

You can familiarize with all controllers below and in Swagger UI documentation under this URL: http://localhost:8081/travel-agency/swagger-ui/index.html.

## 1. Trip configuration

This controller is a configuration page for Admin - the only one who has access to this controller via URL: http://localhost:8081/travel-agency/trip-configuration.
This URL is secured by basic authentication in **Spring security**. The controller has two endpoints:

-> **POST /add-trip** which add validated Trip to repository.
**@Parameter** is required and this is @RequestBody Trip.

-> **PUT /edit-trip** which edit validated Trip in repository.
**@Parameter** is required and this is @RequestBody Trip.

## 2. Main
This controller operates trip management - it enables display collection of trip saved in database by parameter/s given.
This controller is available via URL: http://localhost:8081/travel-agency/main. The controller has four endpoints:

-> **GET /trips-promoted** which return set of trips which is switched as promoted. No parameter to insert.
Example of use: http://localhost:8081/travel-agency/main/trips-promoted

-> **GET /upcoming-trips** which return list of trips in next 30 days, filtered by two optional parameters (if any parameter will be used, the endpoint returns 5 random trips):
 - **@Parameter** **continent-name** is a String parameter which enables filtering list of trips by name of continent eg. "Azja", "Europa", "Ameryka Południowa" - input is case-insensitive and enables searching if contains char sequence in continent names written in database;
 - **@Parameter** **country-name** is a String parameter which enables filtering list of trips by name of continent eg. "Francja", "Polska", "Japonia" - input is case-insensitive and enables searching if contains char sequence in country names written in database;
 Example of use: http://localhost:8081/travel-agency/main/upcoming-trips?continent-name=Azja&country-name=Indie
  
 -> **GET /last-purchased-trips** which return list of 5 trips last purchased by client. No parameter to insert.
 Example of use: http://localhost:8081/travel-agency/main/last-purchased-trips
 
 -> **GET /search-trips** which return list of trips filtered by optional parameters (if any parameter will be used, the endpoint returns 5 random trips):
 - **@Parameter** **continent-name** is a String parameter which enables filtering list of trips by name of continent eg. "Azja", "Europa", "Ameryka Południowa" - input is case-insensitive and enables searching if contains char sequence in continent names written in database;
 - **@Parameter** **country-name** is a String parameter which enables filtering list of trips by name of continent eg. "Francja", "Polska", "Japonia" - input is case-insensitive and enables searching if contains char sequence in country names written in database;
 - **@Parameter** **city-of-departure** is a String parameter which enables filtering list of trips by city name of departure eg. "Wrocław", "Warszawa" - input is case-insensitive and enables searching if contains char sequence in city of departure written in database;
 - **@Parameter** **airport-of-departure** is a String parameter which enables filtering list of trips by airport name of departure eg. "WAW", "KRK" - input is case-insensitive and enables searching if contains char sequence in airport of departure written in database;
 - **@Parameter** **city-of-destination** is a String parameter which enables filtering list of trips by city name of destination eg. "Nowy Jork", "Paryż" - input is case-insensitive and enables searching if contains char sequence in city of destination written in database;
 - **@Parameter** **airport-of-destination** is a String parameter which enables filtering list of trips by airport name of destination eg. "OPO", "NAP" - input is case-insensitive and enables searching if contains char sequence in airport of destination written in database;
 - **@Parameter** **date-of-departure** is a LocalDate parameter which enables filtering list of trips by date of departure by pattern "yyyy-MM-dd" eg. "2023-06-15", "2023-08-10";
 - **@Parameter** **date-of-return** is a LocalDate parameter which enables filtering list of trips by date of return by pattern "yyyy-MM-dd" eg. "2023-06-15", "2023-08-10";
 - **@Parameter** **hotel-number-of-stars** is a Byte parameter which enables filtering list of trips by hotel number of stars from 1 to 5 eg. 3, 4;
 - **@Parameter number-of-days** is a Short parameter which enables filtering list of trips by number of days eg. 8, 10, 15;
 
 Example of URL: http://localhost:8081/travel-agency/main/search-trips?country-name=Australia&city-of-departure=Warszawa&city-of-destination=Sydney&type-of-trip=AI

## 3. Buy Trip
This controller operates purchasing of trip. In order to buy trip, this endpoint need two required path variables: 
- **adults** which looks for number of adults who want to take part in chosen trip;
- **childer** number of children which will take part in chosen trip;
Expect these parameter **JSON @RequestBody** of trip is required. Given trip is validated.
Example of use: http://localhost:8081/travel-agency/buy-trip/2/3
To test this endpoint is reccmmended using Postman or Swagger documentation.

## 4. Technological stack
The app was written using tools bulleted below:
- Java 17;
- Spring boot v.3.0.6;
- MySQL Workbench 8.0 CE.
