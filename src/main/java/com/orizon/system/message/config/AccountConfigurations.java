package com.orizon.system.message.config;

import com.orizon.system.message.domain.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountConfigurations {
    public static User currentUser = null;

    public AccountConfigurations(User user){
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static void login(User user) {
        currentUser = user;
    }
}
