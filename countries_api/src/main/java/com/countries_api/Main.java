package com.countries_api;

import com.countries_api.model.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {

//    public static void main(String[] args) {
//        String path = "doc/countries.json";
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(path)) {
//            // JSON dosyasını okuyun ve Java nesnesine dönüştürün
//            Type countryListType = new TypeToken<List<Country>>() {}.getType();
//            List<Country> countries = gson.fromJson(reader, countryListType);
//
//            // JSON verisini Java nesnelerine dönüştürdük
//            for (Country country : countries) {
//                System.out.println("Country Name: " + country.getName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
