package com.example.domain.value_object.user;

import com.example.domain.shared.ValidationDomainException;

/**
 * 名前未入力例外クラス
 */
public class LastNameNotInputException extends ValidationDomainException {
    public LastNameNotInputException() {
        super("名前が未入力です。");
    }
}
