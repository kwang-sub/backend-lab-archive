package org.example;

import org.example.extension.DataAccessObjectParameterResolver;
import org.example.extension.DatabaseOperationsExtension;
import org.example.extension.ExecutionContextExtension;
import org.example.extension.LogPassengerExistsExceptionExtension;
import org.example.jdbc.PassengerDao;
import org.example.jdbc.PassengerExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({
        ExecutionContextExtension.class,
        DatabaseOperationsExtension.class,
        DataAccessObjectParameterResolver.class,
        LogPassengerExistsExceptionExtension.class
})
class PassengerTest {

    private PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertThat(passenger.toString()).isEqualTo("Passenger John Smith with identifier: 123-456-789");
    }

    @Test
    void testInsertPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        assertThat(passengerDao.getById("123-456-789").getName()).isEqualTo("John Smith");
    }

    @Test
    void testUpdatePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.update("123-456-789", "Michael Smith");
        assertThat(passengerDao.getById("123-456-789").getName()).isEqualTo("Michael Smith");
    }

    @Test
    void testDeletePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.delete(passenger);
        assertThat(passengerDao.getById("123-456-789")).isNull();
    }
}