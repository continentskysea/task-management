// package com.example.domain.entity;

// import java.time.LocalDate;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

import com.example.domain.value_object.task.TaskId;
import com.example.domain.value_object.task.TaskName;
import com.example.domain.value_object.task.due_date.DueDate;
import com.example.domain.value_object.task.priority.Priority;
import com.example.domain.value_object.task.status.Status;
import com.example.domain.value_object.user.UserId;

/**
 * タスク情報のデータクラス
 * @author yoshi
 *
 */


// @Entity
// @Table(name = "tasks", schema = "verification")
public class Task {
    private final TaskId id;
    private final TaskName name;
    private final Priority priority;
    private final DueDate dueDate;
    private final Status status;
    private final User user;


    public Task(
        TaskId id,
        TaskName name,
        Priority priority,
        DueDate dueDate,
        Status status,
        User user
    )  {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.user = user;
    }

    public Task getTask() {
        return new Task(id, name, priority, dueDate, status, user);
    }

// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	@Column(name = "id")
// 	private Long id; // タスクID
	
// 	@Column(name = "name")
// 	private String name; // タスク名
	
// 	@Column(name = "priority")
// 	private Long priority; // 優先順位
	
// 	@Column(name = "due_date") // 期日
// 	private LocalDate dueDate;
	
// 	@Column(name = "user_id")
// 	private Long userId; // ユーザーID
	
// 	// アクセサメソッド
	
// 	// タスクIDを取得する
// 	public Long getId() {
// 		return this.id;
// 	}
	
// 	// タスクIDをセットする
// 	public void setId(Long id) {
// 		this.id = id;
// 	}
	
	
// 	// タスク名を取得する
// 	public String getName() {
// 		return this.name;
// 	}
	
// 	// タスク名をセットする
// 	public void setName(String name) {
// 		this.name = name;
// 	}
	
	
// 	// 優先順位を取得する
// 	public Long getPriority() {
// 		return this.priority;
// 	}
	
// 	// 優先順位をセットする
// 	public void setPriority(Long priority) {
// 		this.priority = priority;
// 	}
	
	
// 	// 期日を取得する
// 	public LocalDate getDueDate() {
// 		return this.dueDate;
// 	}
	
// 	// 期日をセットする
// 	public void setDueDate(LocalDate dueDate) {
// 		this.dueDate = dueDate;
// 	}	
	
	
// 	// ユーザーIDを取得する
// 	public Long getUserId() {
// 		return this.userId;
// 	}
	
// 	// ユーザーIDをセットする
// 	public void setUserId(Long userId) {
// 		this.userId = userId;
// 	}
	
}
