package com.example.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.domain.value_object.task.TaskId;
import com.example.domain.value_object.task.TaskName;
import com.example.domain.value_object.task.due_date.DueDate;
import com.example.domain.value_object.task.priority.Priority;
import com.example.domain.value_object.task.status.Status;
import com.example.domain.value_object.user.UserId;

/**
 * タスク情報集約エンティティ
 * @author yoshi
 *
 */
@Entity
@Table(name = "tasks", schema = "verification")
public class Task {

    /**
     * メンバフィールド
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final TaskId id;

    @Column(name = "name")
    private final TaskName name;

    @Column(name = "priority")
    private final Priority priority;

	@Column(name = "due_date")
    private final DueDate dueDate;

    @Column(name = "status")
    private final Status status;

    @Column(name = "user_id")
    private final UserId userId;


    /**
     * コンストラクタ
     */
    public Task(
        TaskId id,
        TaskName name,
        Priority priority,
        DueDate dueDate,
        Status status,
        UserId userId
    )  {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
    }

    /**
     * オブジェクト生成
     * @return new Task(id, name, priority, dueDate, status, userId);
     */
    public Task getTask() {
        return new Task(id, name, priority, dueDate, status, userId);
    }

}
