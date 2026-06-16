package airport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class PassengerTest {

    @Test
    void testPassengerCreation() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        assertThat(passenger).isNotNull();
    }

    @Test
    void testNonUsPassengerCreation() {
        Passenger passenger = new Passenger("900-45-6789", "John Smith", "GB");
        assertThat(passenger).isNotNull();
    }

    @Test
    void testCreatePassengerWithInvalidSsn() {
        assertThatThrownBy(() -> new Passenger("123-456-789", "John Smith", "US"))
                .isExactlyInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> new Passenger("900-45-6789", "John Smith", "US"))
                .isExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    void testCreatePassengerWithInvalidNonUsIdentifier() {
        assertThatThrownBy(() -> new Passenger("123-45-6789", "John Smith", "GB"))
                .isExactlyInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> new Passenger("900-456-789", "John Smith", "GB"))
                .isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testCreatePassengerWithInvalidCountryCode() {
        assertThatThrownBy(() -> new Passenger("900-45-6789", "John Smith", "GJ"))
                .isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testSetInvalidSsn() {
        assertThatThrownBy(() -> {
            Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
            passenger.setIdentifier("123-456-789");
        }).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testSetValidNonUsIdentifier() {
        Passenger passenger = new Passenger("900-45-6789", "John Smith", "GB");
        passenger.setIdentifier("900-98-7654");
        assertThat(passenger.getIdentifier()).isEqualTo("900-98-7654");
    }

    @Test
    void tesSetInvalidCountryCode() {
        assertThatThrownBy(() -> {
            Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
            passenger.setCountryCode("GJ");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSetValidCountryCode() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        passenger.setCountryCode("GB");
        assertThat(passenger.getCountryCode()).isEqualTo("GB");
    }

    @Test
    void testPassengerToString() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        passenger.setName("John Brown");
        assertThat(passenger.toString()).isEqualTo("Passenger John Brown with identifier: 123-45-6789 from US");
    }
}
