package com.example.ch02;

import com.example.ch02.entity.Message;
import com.example.ch02.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloWorldSpringDataJPATest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void storeLoadMessage() {

        Message message = new Message();
        message.setText("Hello World!");
        messageRepository.save(message);

        List<Message> messages = messageRepository.findAll();

        messages.get(messages.size() - 1).setText("Hello Wold from JPA!");

        assertThat(messages.size()).isEqualTo(1);
        assertThat(messages.get(0).getText()).isEqualTo("Hello Wold from JPA!");
    }
}
