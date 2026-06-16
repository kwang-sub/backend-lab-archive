package org.example.ex07.base;

public class MessageBus {
    private static IBus bus;

    public static void sendEmailChangedMessage(int userId, String newEmail) {
        bus.send("Subject: USER; Type: EMAIL CHANGED; Id: " + userId + "; NewEmail: " + newEmail);
    }
}