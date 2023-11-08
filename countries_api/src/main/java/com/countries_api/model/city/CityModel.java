package com.countries_api.model.city;

import lombok.Data;

@Data
public class CityModel {

    private Long id;

    private String country;

    private String city;

    private double latitude;

    private double longitude;

    private double altitude;
}
