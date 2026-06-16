package airport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class FlightTest {

    @Test
    void testFlightCreation() {
        Flight flight = new Flight("AA123", 100);
        assertThat(flight).isNotNull();
    }

    @Test
    void testInvalidFlightNumber() {
        assertThatThrownBy(() -> new Flight("AA12", 100)).isExactlyInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> new Flight("AA12345", 100)).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testValidFlightNumber() {
        Flight flight = new Flight("AA345", 100);
        assertThat(flight).isNotNull();
        flight = new Flight("AA3465", 100);
        assertThat(flight).isNotNull();
    }

    @Test
    void testAddPassengers() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        for (int i = 0; i < flight.getSeats(); i++) {
            flight.addPassenger();
        }
        assertThat(flight.getPassengers()).isEqualTo(50);
        assertThatThrownBy(flight::addPassenger).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testSetInvalidSeats() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        for (int i = 0; i < flight.getSeats(); i++) {
            flight.addPassenger();
        }
        assertThat(flight.getPassengers()).isEqualTo(50);
        flight.setSeats(52);
        assertThat(flight.getSeats()).isEqualTo(52);
    }

    @Test
    void testChangeOrigin() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();
        assertThat(flight.isFlying()).isTrue();
        assertThat(flight.isTakenOff()).isTrue();
        assertThat(flight.isLanded()).isFalse();
        assertThatThrownBy(() -> flight.setOrigin("Manchester")).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testChangeDestination() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();
        flight.land();
        assertThatThrownBy(() -> flight.setDestination("Sibiu")).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void testLand() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();
        assertThat(flight.isTakenOff()).isTrue();
        assertThat(flight.isLanded()).isFalse();
        flight.land();
        assertThat(flight.isTakenOff()).isTrue();
        assertThat(flight.isLanded()).isTrue();
        assertThat(flight.isFlying()).isFalse();
    }
}
