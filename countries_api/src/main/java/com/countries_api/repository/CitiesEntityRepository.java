package com.countries_api.repository;

import com.countries_api.entity.CitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesEntityRepository extends JpaRepository<CitiesEntity, Long> {
}