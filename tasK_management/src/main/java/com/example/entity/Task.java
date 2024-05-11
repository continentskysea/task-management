package com.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * タスク情報のデータクラス
 * @author yoshi
 *
 */
@Entity
@Table(name = "TASKS")
public class Task {
	@Id
	@SequenceGenerator(name = "TASKS_ID_GENERATOR", sequenceName = "TASKS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASKS_ID_GENERATOR")
	@Column(name = "ID")
	private Long id; // タスクID
	
	@Column(name = "NAME")
	private String name; // タスク名
	
	@Column(name = "PRIORITY")
	private Long priority; // 優先順位
	
	@Column(name = "DUE_DATE") // 期日
	private LocalDate dueDate;
	
	@Column(name = "USER_ID")
	private Long userId; // ユーザーID
	
	// アクセサメソッド
	
	// タスクIDを取得する
	public Long getId() {
		return this.id;
	}
	
	// タスクIDをセットする
	public void setId(Long id) {
		this.id = id;
	}
	
	
	// タスク名を取得する
	public String getName() {
		return this.name;
	}
	
	// タスク名をセットする
	public void setName(String name) {
		this.name = name;
	}
	
	
	// 優先順位を取得する
	public Long getPriority() {
		return this.priority;
	}
	
	// 優先順位をセットする
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
	
	// 期日を取得する
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	// 期日をセットする
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}	
	
	
	// ユーザーIDを取得する
	public Long getUserId() {
		return this.userId;
	}
	
	// ユーザーIDをセットする
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
