package com.mkrzyszczyk.shop.common.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSimpleService implements EmailSender {

  private final JavaMailSender mailSender;

  @Async
  @Override
  public void send(String to, String subject, String msg) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("Shop <TurboShop@turboapp.com>");
    message.setReplyTo("Shop <TurboShop@turboapp.com>");
    message.setTo();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(msg);
    mailSender.send(message);
  }
}
