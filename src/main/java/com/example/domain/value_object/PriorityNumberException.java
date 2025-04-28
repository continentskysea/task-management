package com.example.domain.value_object;

import com.example.domain.shared.ValidationDomainException;

class PriorityNumberException extends ValidationDomainException {
    public PriorityNumberException() {
        super("優先順位は1から30までの数字を入力してください");
    }
}
