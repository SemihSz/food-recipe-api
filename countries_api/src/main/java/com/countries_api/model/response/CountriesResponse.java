package com.countries_api.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CountriesResponse {

    private int id;

    private String name;

    private String region;

    private String regionId;

    private String nationality;
}
