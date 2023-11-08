package com.countries_api.mapper.country;

import com.countries_api.entity.CountryEntity;
import com.countries_api.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    Country convert(CountryEntity country);

    List<Country> convert(List<CountryEntity> entityList);
}
