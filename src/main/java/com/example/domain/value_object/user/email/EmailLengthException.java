package com.example.domain.value_object.user.email;

import com.example.domain.shared.ValidationDomainException;

class EmailLengthException extends ValidationDomainException {
    public EmailLengthException() {
        super("メールアドレスは50文字以内で入力してください。");
    }
}
