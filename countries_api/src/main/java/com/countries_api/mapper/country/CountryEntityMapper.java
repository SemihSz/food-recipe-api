package com.countries_api.mapper.country;

import com.countries_api.entity.CountryEntity;
import com.countries_api.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryEntityMapper {
    CountryEntityMapper INSTANCE = Mappers.getMapper(CountryEntityMapper.class);

    CountryEntity convert(Country country);

    List<CountryEntity> convert(List<Country> countries);
}
