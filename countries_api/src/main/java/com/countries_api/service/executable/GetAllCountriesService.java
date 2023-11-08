package com.countries_api.service.executable;

import com.countries_api.entity.CountryEntity;
import com.countries_api.mapper.country.CountryMapper;
import com.countries_api.model.Country;
import com.countries_api.repository.CountryEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllCountriesService implements Supplier<List<Country>> {

    private final CountryEntityRepository countryEntityRepository;

    @Override
    public List<Country> get() {

        final List<CountryEntity> allCountriesEntityList = countryEntityRepository.findAll();
        return CountryMapper.INSTANCE.convert(allCountriesEntityList);
    }
}
