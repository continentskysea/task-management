package com.example.domain.value_object.timer;

import com.example.domain.shared.ValidationDomainException;

public class FocusTimerNotInputException extends ValidationDomainException {
    public FocusTimerNotInputException(String message) {
        super(message);
    }
}
