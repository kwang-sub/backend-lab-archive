package org.example.ex07.canexecute;

public class MessageBus {
    private IBus bus;

    public void sendEmailChangedMessage(int userId, String newEmail) {
        bus.send("Subject: USER; Type: EMAIL CHANGED; Id: " + userId + "; NewEmail: " + newEmail);
    }
}