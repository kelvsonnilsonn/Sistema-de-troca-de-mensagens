package com.orizon.system.message.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Password {
    private String pass;

    public Password(String pass) {
        validate(pass);
        this.pass = pass;
    }

    private void validate(String pass) {
        if (pass == null || pass.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        if (pass.length() < 3) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 3 caracteres");
        }
    }
}
