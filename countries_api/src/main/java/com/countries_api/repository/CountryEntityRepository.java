package com.countries_api.repository;

import com.countries_api.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryEntityRepository extends JpaRepository<CountryEntity, Integer> {
}