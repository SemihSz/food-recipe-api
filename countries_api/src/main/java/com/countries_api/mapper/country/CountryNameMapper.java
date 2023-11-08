package com.countries_api.mapper.country;

import com.countries_api.model.Country;
import com.countries_api.model.response.CountriesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryNameMapper {

    CountryNameMapper INSTANCE = Mappers.getMapper(CountryNameMapper.class);

    List<CountriesResponse> getAllCountriesName(List<Country> countryList);

    default CountriesResponse getCountryName(Country country) {
        return CountriesResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .nationality(country.getNationality())
                .region(country.getRegion())
                .regionId(country.getRegionId()).build();
    }
}
