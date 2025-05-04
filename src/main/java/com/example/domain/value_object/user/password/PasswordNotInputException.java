package com.example.domain.value_object.user.password;

import com.example.domain.shared.ValidationDomainException;

public class PasswordNotInputException extends ValidationDomainException {
    public PasswordNotInputException() {
        super("パスワードが未入力です。");
    }
}
