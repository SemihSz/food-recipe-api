package com.countries_api.mapper.cities;

import com.countries_api.entity.CitiesEntity;
import com.countries_api.model.city.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CitiesEntityMapper {

    CitiesEntityMapper INSTANCE = Mappers.getMapper(CitiesEntityMapper.class);

    CitiesEntity convert(CityModel cityModel);

    List<CitiesEntity> convert(List<CityModel> cityModels);
}
