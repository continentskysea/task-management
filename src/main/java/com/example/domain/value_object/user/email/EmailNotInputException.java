package com.example.domain.value_object.user.email;

import com.example.domain.shared.ValidationDomainException;

class EmailNotInputException extends ValidationDomainException {
    public EmailNotInputException() {
        super("メールアドレスが未入力です。");
    }
}
