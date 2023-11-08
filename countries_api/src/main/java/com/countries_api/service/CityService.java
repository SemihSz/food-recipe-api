package com.countries_api.service;

import com.countries_api.model.city.CityModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    List<CityModel> getAllCities();

    void saveCities();
}
