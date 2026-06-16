package org.example.ssiach11ex1s1.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ssiach11ex1s1.entity.Otp;
import org.example.ssiach11ex1s1.entity.Users;
import org.example.ssiach11ex1s1.repository.OtpRepository;
import org.example.ssiach11ex1s1.repository.UserRepository;
import org.example.ssiach11ex1s1.util.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;

    public void addUser(final Users user) {
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(final Users user) {
        final Users findUser = userRepository.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials."));
        if (passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            renewOtp(findUser);
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }

    }

    private void renewOtp(final Users user) {
        final String code = GenerateCodeUtil.generateCode();
        final Optional<Otp> userOtp = otpRepository.findByUsername(user.getUsername());
        Otp otp;
        if (userOtp.isPresent()) {
            otp = userOtp.get();
            otp.updateCode(code);
        } else {
            otp = Otp.create(user.getUsername(), code);
        }
        log.info("New OTP code: {}", otp.getCode());
        otpRepository.save(otp);
    }

    public boolean check(final Otp otpToValidate) {
        final Optional<Otp> findOtp = otpRepository.findByUsername(otpToValidate.getUsername());
        if (findOtp.isPresent()) {
            String ogCode = findOtp.get().getCode();
            if (ogCode.equals(otpToValidate.getCode())) {
                return true;
            }
        }
        return false;
    }
}
