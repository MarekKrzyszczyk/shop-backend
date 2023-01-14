package com.mkrzyszczyk.shop.common.mail;

import org.springframework.stereotype.Service;

@Service
public class WebServiceMailService implements EmailSender {

    @Override
    public void send(String to, String subject, String msg) {
        throw new RuntimeException("Not implemented yet");
    }
}
