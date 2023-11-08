package com.countries_api.service.impl;

import com.countries_api.model.city.CityModel;
import com.countries_api.service.CityService;
import com.countries_api.service.executable.SaveCitiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final SaveCitiesService saveCitiesService;

    @Override
    public List<CityModel> getAllCities() {
        return null;
    }

    @Override
    public void saveCities() {
        saveCitiesService.get();
    }
}
