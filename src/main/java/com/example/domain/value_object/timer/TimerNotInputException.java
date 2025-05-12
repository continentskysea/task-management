package com.example.domain.value_object.timer;

import com.example.domain.shared.ValidationDomainException;

public class TimerNotInputException extends ValidationDomainException {
    public TimerNotInputException(String message) {
        super(message);
    }
}
