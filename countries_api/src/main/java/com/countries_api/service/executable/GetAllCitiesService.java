package com.countries_api.service.executable;

import com.countries_api.mapper.cities.CityMapper;
import com.countries_api.model.city.CityModel;
import com.countries_api.repository.CitiesEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCitiesService implements Supplier<List<CityModel>> {

    private final CitiesEntityRepository citiesEntityRepository;

    @Override
    public List<CityModel> get() {
        return CityMapper.INSTANCE.getAllCities(citiesEntityRepository.findAll());
    }
}
