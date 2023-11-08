package com.countries_api.service;

import com.countries_api.model.Country;
import com.countries_api.model.response.CountriesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    List<Country> getAllCountries();

    List<CountriesResponse> getAllCountriesName();

    void saveCountries();
}
