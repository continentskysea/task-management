package com.example.domain.value_object.timer;

import com.example.domain.shared.ValidationDomainException;

public class FocusTimerFormatException extends ValidationDomainException {
    public FocusTimerFormatException(String message) {
        super(message);
    }
}
