package com.example.domain.value_object.user;

import com.example.domain.shared.ValidationDomainException;

/**
 * 苗字未入力例外クラス
 */
public class FirstNameNotInputException extends ValidationDomainException {
    public FirstNameNotInputException(String message) {
        super(message);
    }
}
