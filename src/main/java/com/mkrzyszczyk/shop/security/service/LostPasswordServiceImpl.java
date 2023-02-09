package com.mkrzyszczyk.shop.security.service;

import com.mkrzyszczyk.shop.common.mail.EmailClientService;
import com.mkrzyszczyk.shop.security.model.User;
import com.mkrzyszczyk.shop.security.model.dto.ChangePassword;
import com.mkrzyszczyk.shop.security.model.dto.EmailObject;
import com.mkrzyszczyk.shop.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LostPasswordServiceImpl implements LostPasswordService {

    private final UserRepository userRepository;
    private final EmailClientService emailClientService;

    @Value("${app.serviceAddress}")
    private String serviceAddress;

    @Override
    @Transactional
    public void sendLostPasswordLink(EmailObject email) {
        User user = userRepository.findByUsername(email.getEmail())
                .orElseThrow(() -> new RuntimeException("Email doesn't exist!"));
        String hash = generateHashForLostPassword(user);
        user.setHash(hash);
        user.setHashDate(LocalDateTime.now());
        emailClientService.getInstance()
                .send(email.getEmail(), "Reset password", createMessage(createLink(hash)));
    }

    @Override
    @Transactional
    public void changePassword(ChangePassword changePassword) {
        if (!Objects.equals(changePassword.getPassword(),
                changePassword.getRepeatPassword())) {
            throw new RuntimeException("Hasła nie są takie same");
        }
        User user = userRepository.findByHash(changePassword.getHash())
                .orElseThrow(() -> new
                        RuntimeException("Nieprawidłowy link"));
        if (user.getHashDate().plusMinutes(10)
                .isAfter(LocalDateTime.now())) {
            user.setPassword("{bcrypt}" + new
                    BCryptPasswordEncoder().encode(changePassword.getPassword()));
            user.setHash(null);
            user.setHashDate(null);
        } else {
            throw new RuntimeException("Link stracił ważność");
        }
    }

    private String createLink(String hash) {
        return serviceAddress + "/lostPassword/" + hash;
    }

    private String createMessage(String hashLink) {
        return "We have generated for you link to reset your password" +
                "\n\nClick on link to reset password: " +
                "\n" + hashLink +
                "\n\nThank you.";
    }

    private String generateHashForLostPassword(User user) {
        String toHash = user.getId() + user.getUsername() + user.getPassword() + LocalDateTime.now();
        return DigestUtils.sha256Hex(toHash);
    }
}
