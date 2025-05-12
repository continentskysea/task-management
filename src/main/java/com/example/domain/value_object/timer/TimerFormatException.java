package com.example.domain.value_object.timer;

import com.example.domain.shared.ValidationDomainException;

public class TimerFormatException extends ValidationDomainException {
    public TimerFormatException(String message) {
        super(message);
    }
}
