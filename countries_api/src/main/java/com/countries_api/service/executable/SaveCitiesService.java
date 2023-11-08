package com.countries_api.service.executable;

import com.countries_api.entity.CitiesEntity;
import com.countries_api.mapper.cities.CitiesEntityMapper;
import com.countries_api.model.city.CityModel;
import com.countries_api.repository.CitiesEntityRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveCitiesService implements Supplier<Boolean> {

    private final CitiesEntityRepository citiesEntityRepository;

    @Override
    public Boolean get() {
        String path = "doc/cities.json";
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            Type cityListType = new TypeToken<List<CityModel>>() {}.getType();
            List<CityModel> cities = gson.fromJson(reader, cityListType);
            List<CitiesEntity> citiesEntities = CitiesEntityMapper.INSTANCE.convert(cities);

            citiesEntityRepository.saveAll(citiesEntities);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
