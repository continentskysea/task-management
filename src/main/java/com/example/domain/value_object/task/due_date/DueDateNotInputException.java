package com.example.domain.value_object.task.due_date;

import com.example.domain.shared.ValidationDomainException;

public class DueDateNotInputException extends ValidationDomainException{
    public DueDateNotInputException() {
        super("期日が未入力です。");
    }
}
