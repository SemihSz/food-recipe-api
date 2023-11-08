package com.countries_api.controller;

import com.countries_api.model.city.CityModel;
import com.countries_api.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CitiesController {

    private final CityService cityService;

    @GetMapping("/list")
    public ResponseEntity<List<CityModel>> getAllCities() {

        return ResponseEntity.ok(cityService.getAllCities());
    }

//    @GetMapping("/save")
//    public ResponseEntity<Boolean> saveCitiesJson() {
//
//        cityService.saveCities();
//
//        return ResponseEntity.ok(Boolean.TRUE);
//    }
}
