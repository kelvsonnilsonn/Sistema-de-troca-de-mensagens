package com.orizon.system.message.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@Getter
public class Password {
    private String pass;

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])[a-zA-Z0-9]{3,}";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    public Password(String pass) {
        validate(pass);
        this.pass = pass;
    }

    private void validate(String pass) {
        if (!pattern.matcher(pass).matches()) {
            throw new IllegalArgumentException("A senha não segue o padrão: \n" +
                    "Começar com uma letra maiúscula e ter tamanho de pelo menos 3 caracteres, podendo conter letras e números");
        }
    }
}
