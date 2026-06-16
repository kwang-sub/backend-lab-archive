package org.example.ch18restfulapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.example.ch18restfulapi.beans.FlightBuilder;
import org.example.ch18restfulapi.exceptions.PassengerNotFoundException;
import org.example.ch18restfulapi.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(FlightBuilder.class)
public class RestApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    @MockitoBean
    private PassengerRepository passengerRepository;

    @MockitoBean
    private CountryRepository countryRepository;

    @Test
    void testGetAllCountries() throws Exception {
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));
        mvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect((jsonPath("$[0].name").exists()));
    }

    @Test
    void testGetAllPassengers() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));
        mvc.perform(get("/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(20));
    }

    @Test
    void testPassengerNotFound() {
        Throwable throwable = catchThrowable(() ->
                mvc.perform(get("/passengers/1"))
                        .andExpect(status().isNotFound())
        );

        assertThat(throwable)
                .isInstanceOf(ServletException.class)
                .hasCauseExactlyInstanceOf(PassengerNotFoundException.class);
    }

    @Test
    void testPostPassenger() throws Exception {
        Passenger passenger = new Passenger("Peter Michelsen");
        passenger.setCountry(countriesMap.get("US"));
        passenger.setIsRegistered(false);
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        mvc.perform(post("/passengers")
                        .content(new ObjectMapper().writeValueAsString(passenger))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Peter Michelsen"))
                .andExpect(jsonPath("$.country").value(countriesMap.get("US")))
                .andExpect(jsonPath("$.country.name").value("USA"))
                .andExpect(jsonPath("$.registered").value(Boolean.FALSE));
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testPatchPassenger() throws Exception {
        Passenger passenger = new Passenger("Sophia Graham");
        passenger.setCountry(countriesMap.get("UK"));
        passenger.setIsRegistered(false);
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        Passenger updateData = new Passenger("Sophia Jones");
        updateData.setCountry(countriesMap.get("AU"));
        updateData.setIsRegistered(true);

//        String updated = "{\name\"}"

        mvc.perform(patch("/passengers/1")
                        .content(new ObjectMapper().writeValueAsString(updateData))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        verify(passengerRepository, times(1)).save(passenger);
        verify(passengerRepository, times(1)).findById(1L);
    }


    @Test
    void testPatchPassenger2() throws Exception {
        Passenger passenger = new Passenger("Sophia Graham");
        passenger.setCountry(countriesMap.get("UK"));
        passenger.setIsRegistered(false);
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        String updates =
                "{\"name\":\"Sophia Jones\", \"country\":\"AU\", \"isRegistered\":\"true\"}";

        mvc.perform(patch("/passengers/1")
                        .content(updates)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).findById(1L);
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testDeletedPassenger() throws Exception {
        mvc.perform(delete("/passengers/4"))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).deleteById(4L);
    }
}
