package com.orizon.system.message.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@Getter
public class Username {
    public String username;

    public static final String REGEX = "^(?=.*[a-zA-Z])[a-zA-Z0-9_]{3,}$";
    private static final Pattern pattern = Pattern.compile(REGEX);

    public Username(String username) {
        validate(username);
        this.username = username;
    }

    public void validate(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário não pode ser nulo ou vazio");
        }
        if (!pattern.matcher(username).matches()) {
            throw new IllegalArgumentException("O nome de usuário não segue o padrão: \n" +
                    "Ao menos 1 letra e tamanho pelo menos 3 caracteres, podendo conter letras, números e _");
        }
    }

}
