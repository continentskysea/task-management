package com.example.domain.value_object.user.email;

import com.example.domain.shared.ValidationDomainException;

class EmailDomainFormatException extends ValidationDomainException {
    public EmailDomainFormatException() {
        super("メールアドレスに @example.com のドメインが含まれておりません。");
    }
}
