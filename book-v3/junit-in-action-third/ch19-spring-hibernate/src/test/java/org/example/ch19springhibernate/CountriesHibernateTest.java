package org.example.ch19springhibernate;

import org.example.ch19springhibernate.model.Country;
import org.example.ch19springhibernate.model.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.ch19springhibernate.model.CountryService.COUNTRY_INIT_DATA;

@SpringBootTest
public class CountriesHibernateTest {
    @Autowired
    private CountryService countryService;

    private final List<Country> expectedCountryList = new ArrayList<>();
    private final List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        countryService.init();
        initExpectedCountryLists();
    }

    @Test
    void testCountryList() {
        List<Country> countryList = countryService.getAllCountries();
        assertThat(countryList).isNotNull();
        assertThat(countryList.size()).isEqualTo(expectedCountryList.size());

        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertThat(countryList.get(i)).isEqualTo(expectedCountryList.get(i));
        }
    }

    @Test
    void testCountryListStartsWithA() {
        List<Country> resultList = countryService.getCountriesStartingWithA();
        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(expectedCountryListStartsWithA.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertThat(resultList.get(i)).isEqualTo(expectedCountryListStartsWithA.get(i));
        }
    }

    @AfterEach
    public void tearDown() {
        countryService.clear();
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
