package org.example.firstservice.firstservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
@RequiredArgsConstructor
public class FirstServiceController {

    private final Environment environment;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to FirstService!";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String name) {
        log.info(name);
        return "message from FirstService!";
    }

    @GetMapping("/check")
    public String check() {
        log.info(environment.getProperty("local.server.port"));
        return "check first service" + environment.getProperty("local.server.port");
    }

}
