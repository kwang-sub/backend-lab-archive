package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;

    @Value("${order_service.url}")
    private String orderServiceUrl;

    public ResponseUser createUser(RequestUser userDto) {
        User user = userMapper.toEntity(userDto);
        user.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);

        return getUserByUserId(user.getUserId());
    }

    @Transactional(readOnly = true)
    public ResponseUser getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);

        ResponseUser dto = userMapper.toDto(user);
        String orderUrl = String.format(orderServiceUrl, userId);
//        ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(
//                orderUrl,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<ResponseOrder>>() {
//                });
//        dto.setOrders(orderListResponse.getBody());
//        try {
//            dto.setOrders(orderServiceClient.getOrders(userId));
//        } catch (FeignException e) {
//            log.error(e.getMessage());
//            dto.setOrders(new ArrayList<>());
//        }
        dto.setOrders(orderServiceClient.getOrders(userId));
        return dto;
    }

    @Transactional(readOnly = true)
    public List<ResponseUser> getUserByAll() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto)
                .toList();
    }

    public ResponseUser getUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto).orElseThrow(EntityNotFoundException::new);
    }
}
