package com.example.domain.value_object.user.name;

import com.example.domain.shared.ValidationDomainException;

/**
 * 苗字形式例外クラス
 */
public class FirstNameInputFormatException extends ValidationDomainException {
    public FirstNameInputFormatException(String message) {
        super(message);
    }
}
