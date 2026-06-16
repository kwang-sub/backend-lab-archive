package org.example.extension;

import org.example.jdbc.ConnectionManager;
import org.example.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;

public class DatabaseOperationsExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    private Connection connection;
    private Savepoint savepoint;


    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        ConnectionManager.closeConnection();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        connection.rollback(savepoint);
    }
}
