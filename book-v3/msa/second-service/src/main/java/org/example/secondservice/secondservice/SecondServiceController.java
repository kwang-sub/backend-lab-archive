package org.example.secondservice.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to SecondService!";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String name) {
        log.info(name);
        return "message from SecondService!";
    }


    @GetMapping("/check")
    public String check() {
        return "check second service";
    }
}
