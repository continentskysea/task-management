package com.example.entity;

import java.time.LocalDate;

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
	private Integer id; // タスクID
	
	@Column(name = "NAME")
	private String name; // タスク名
	
	@Column(name = "PRIORITY")
	private Integer priority; // 優先順位
	
	@Column(name = "DUE_DATE") // 期日
	private LocalDate dueDate;
	
	@Column(name = "USER_ID")
	private Integer userId; // ユーザーID
	
	// アクセサメソッド
	
	// タスクIDを取得する
	public Integer getId() {
		return this.id;
	}
	
	// タスクIDをセットする
	public void setId(Integer id) {
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
	public Integer getPriority() {
		return this.priority;
	}
	
	// 優先順位をセットする
	public void setPriority(Integer priority) {
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
	public Integer getUserId() {
		return this.userId;
	}
	
	// ユーザーIDをセットする
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
