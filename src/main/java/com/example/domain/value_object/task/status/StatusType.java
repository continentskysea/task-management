package com.example.domain.value_object.task.status;

/**
 * 列挙型
 */
enum StatusType {
    notStarted(new NotStartedStatus()),
    inProcess(new  InProcessStatus()),
    completed(new  CompletedStatus());

    private Status status;
    
    private StatusType(Status status) {
        this.status = status;
    }

    public String getStatusType() {
        return this.status.label();
    }
}
