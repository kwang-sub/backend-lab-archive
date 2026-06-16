package org.example.ch19jdbc;

import org.assertj.core.api.Assertions;
import org.example.ch19jdbc.dao.CountryDao;
import org.example.ch19jdbc.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.example.ch19jdbc.CountriesLoader.COUNTRY_INIT_DATA;

public class CountriesDatabaseTest {

    private CountryDao countryDao = new CountryDao();
    private CountriesLoader countriesLoader = new CountriesLoader();

    private List<Country> expectedCountryList = new ArrayList();
    private List<Country> expectedCountryListStartsWithA = new ArrayList();

    @BeforeEach
    public void setUp() {
        TablesManager.createTable();
        initExpectedCountryLists();
        countriesLoader.loadCountries();
    }

    @Test
    void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertThat(countryList).isNotNull();
        assertThat(countryList.size()).isEqualTo(expectedCountryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertThat(expectedCountryList.get(i)).isEqualTo(countryList.get(i));
        }
    }

    @Test
    void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertThat(countryList).isNotNull();
        assertThat(expectedCountryListStartsWithA.size()).isEqualTo(countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertThat(expectedCountryListStartsWithA.get(i)).isEqualTo(countryList.get(i));
        }
    }

    @AfterEach
    public void tearDown() {
        TablesManager.dropTable();
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
