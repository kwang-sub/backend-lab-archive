package org.example.ssiach11ex1s1.controller;

import lombok.RequiredArgsConstructor;
import org.example.ssiach11ex1s1.entity.Otp;
import org.example.ssiach11ex1s1.entity.Users;
import org.example.ssiach11ex1s1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody Users user) {
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody Users user) {
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public ResponseEntity<Object> check(@RequestBody Otp otp) {
        if (userService.check(otp)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
