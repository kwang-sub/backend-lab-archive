package org.example.ch19hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CountriesHibernateTest {

    @Autowired
    private EntityManagerFactory emf;
    private EntityManager em;

    private List<Country> expectedCountryList = new ArrayList<Country>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList();

    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}};

    @BeforeEach
    public void setUp() {
        initExpectedCountryLists();
        em = emf.createEntityManager();

        // 기존 데이터 삭제
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Country").executeUpdate();
        em.getTransaction().commit();

        em.getTransaction().begin();
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            em.persist(country);
        }

        em.getTransaction().commit();
    }

    @Test
    void testCountryList() {
        List<Country> countryList = em.createQuery("select c from Country c").getResultList();
        assertThat(countryList).isNotNull();
        assertThat(countryList.size()).isEqualTo(expectedCountryList.size());

        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertThat(countryList.get(i)).isEqualTo(expectedCountryList.get(i));
        }
    }

    @Test
    void testCountryListStartsWithA() {
        List<Country> resultList = em.createQuery("select c from Country c where c.name like 'A%'").getResultList();
        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(expectedCountryListStartsWithA.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertThat(resultList.get(i)).isEqualTo(expectedCountryListStartsWithA.get(i));
        }
    }

    @AfterEach
    public void tearDown() {
        em.close();
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
