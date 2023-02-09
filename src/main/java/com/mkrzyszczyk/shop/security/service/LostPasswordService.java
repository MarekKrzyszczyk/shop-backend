package com.mkrzyszczyk.shop.security.service;

import com.mkrzyszczyk.shop.security.model.dto.ChangePassword;
import com.mkrzyszczyk.shop.security.model.dto.EmailObject;

public interface LostPasswordService {

    void sendLostPasswordLink(EmailObject email);

    void changePassword(ChangePassword changePassword);
}
