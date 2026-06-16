package org.example.extension;

import org.example.jdbc.PassengerExistsException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof PassengerExistsException) {
            log.severe("Passenger exists " + throwable.getMessage());
            return;
        }
        throw throwable;
    }
}
