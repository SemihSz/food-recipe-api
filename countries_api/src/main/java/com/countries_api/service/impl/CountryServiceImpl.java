package com.countries_api.service.impl;

import com.countries_api.mapper.country.CountryNameMapper;
import com.countries_api.model.Country;
import com.countries_api.model.response.CountriesResponse;
import com.countries_api.service.CountryService;
import com.countries_api.service.executable.GetAllCountriesService;
import com.countries_api.service.executable.SaveCountiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final GetAllCountriesService getAllCountriesService;

    private final SaveCountiesService saveCountiesService;

    @Override
    public List<Country> getAllCountries() {
        return getAllCountriesService.get();
    }

    @Override
    public List<CountriesResponse> getAllCountriesName() {

        final List<Country> countryList = getAllCountriesService.get();
        return CountryNameMapper.INSTANCE.getAllCountriesName(countryList);
    }

    @Override
    public void saveCountries() {
        saveCountiesService.get();
    }
}
