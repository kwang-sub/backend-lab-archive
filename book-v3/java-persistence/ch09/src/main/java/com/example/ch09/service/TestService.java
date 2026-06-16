package com.example.ch09.service;

import com.example.ch09.model.Address;
import com.example.ch09.model.User;
import com.example.ch09.repository.AddressRepository;
import com.example.ch09.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public void storeLoadEntities() {
        Address address = Address.builder()
                .street("Flowers Street")
                .zipcode("01246")
                .city("Boston")
                .build();
        addressRepository.save(address);
        User user = User.builder()
                .username("John Smith")
                .shippingAddress(address)
                .build();
        userRepository.save(user);
    }
}
