package com.example.domain.value_object.user;

import com.example.domain.shared.ValidationDomainException;

class UserNameCharacterLimitException extends ValidationDomainException   {
    public UserNameCharacterLimitException() {
        super("ユーザー名は3文字以上12文字以下で入力してください。");
    }
}