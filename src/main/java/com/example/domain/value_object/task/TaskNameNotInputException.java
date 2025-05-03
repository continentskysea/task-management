package com.example.domain.value_object.task;

import com.example.domain.shared.ValidationDomainException;

class TaskNameNotInputException extends ValidationDomainException {
    public TaskNameNotInputException() {
        super("タスク名が未入力です");
    }
}
