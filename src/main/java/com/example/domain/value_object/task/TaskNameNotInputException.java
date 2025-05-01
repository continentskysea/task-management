package com.example.domain.value_object.task;

class TaskNameNotInputException extends ValidationDomainException {
    public TaskNameNotInputException() {
        super("タスク名が未入力です");
    }
}
