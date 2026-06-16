package org.example.ch19springjdbc;

import org.assertj.core.api.Assertions;
import org.example.ch19springjdbc.dao.CountryDao;
import org.example.ch19springjdbc.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CountriesDatabaseTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CountriesLoader countriesLoader;

    private List<Country> expectedCountryList = new ArrayList<Country>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList();

    @BeforeEach
    public void setUp() {
        initExpectedCountryLists();
        countriesLoader.loadCountries();
    }

    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();
        assertThat(countryList).isNotNull();
        assertThat(countryList.size()).isEqualTo(expectedCountryList.size());
        for (int i = 0; i < countryList.size(); i++) {
            assertThat(countryList.get(i)).isEqualTo(expectedCountryList.get(i));
        }
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");
        assertThat(countryList).isNotNull();
        assertThat(countryList.size()).isEqualTo(expectedCountryListStartsWithA.size());
        for (int i = 0; i < countryList.size(); i++) {
            assertThat(countryList.get(i)).isEqualTo(expectedCountryListStartsWithA.get(i));
        }
    }


    private void initExpectedCountryLists() {
        for (int i = 0; i < CountriesLoader.COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = CountriesLoader.COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
