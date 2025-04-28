package com.example.domain.value_object;

import com.example.domain.shared.ValidationDomainException;

class PriorityNotInputException extends ValidationDomainException {
    public PriorityNotInputException() {
        super("優先順位が未入力です。");
    }
}