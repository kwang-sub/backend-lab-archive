package org.example.ex05;

public class Controller {
    private final IEmailGateway emailGateway;
    private final IDatabase database;

    public Controller(IEmailGateway emailGateway, IDatabase database) {
        this.emailGateway = emailGateway;
        this.database = database;
    }

    public void greetUser(String userEmail) {
        emailGateway.sendGreetingsEmail(userEmail);
    }

    public Report createReport() {
        int numberOfUsers = database.getNumberOfUsers();
        return new Report(numberOfUsers);
    }
}