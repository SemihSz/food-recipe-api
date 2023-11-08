package com.countries_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String iso3;
    private String iso2;
    private String numericCode;
    private String phoneCode;
    private String capital;
    private String currency;
    private String currencyName;
    private String currencySymbol;
    private String tld;
    private String nativeName;
    private String region;
    private String regionId;
    private String subregion;
    private String subregionId;
    private String nationality;
    private String latitude;
    private String longitude;
    private String emoji;
    private String emojiU;
}




