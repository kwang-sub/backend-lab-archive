package org.example.market.service;

import lombok.RequiredArgsConstructor;
import org.example.market.common.ApplicationConstant;
import org.example.market.dto.response.CustomerResDto;
import org.example.market.entity.Customer;
import org.example.market.exception.AlreadyFieldException;
import org.example.market.exception.EntityNotFoundException;
import org.example.market.mapper.CustomerMapper;
import org.example.market.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResDto create(final String name, final String password) {
        Optional<Customer> sameNameCustomer = customerRepository.findByName(name);
        if (sameNameCustomer.isPresent())
            throw new AlreadyFieldException(ApplicationConstant.Exception.EXCEPTION_PARAM_NAME);

        Customer customer = Customer.create(name, passwordEncoder.encode(password));
        customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }

    @Transactional(readOnly = true)
    public Customer findEntityById(final Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, id));
    }
}
