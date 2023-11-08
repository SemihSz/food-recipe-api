package com.countries_api.service.executable;

import com.countries_api.entity.CountryEntity;
import com.countries_api.mapper.country.CountryEntityMapper;
import com.countries_api.model.Country;
import com.countries_api.repository.CountryEntityRepository;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveCountiesService implements Supplier<Boolean> {

    private final CountryEntityRepository countryEntityRepository;
    @Override
    public Boolean get() {
        String path = "doc/countries.json";
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            Type countryListType = new TypeToken<List<Country>>() {}.getType();
            List<Country> countries = gson.fromJson(reader, countryListType);
            List<CountryEntity> countryEntities = CountryEntityMapper.INSTANCE.convert(countries);

            countryEntityRepository.saveAll(countryEntities);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
