package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * タイマー設定用のデータクラス
 * @author yoshi
 *
 */
@Entity
@Table(name = "timers_settings", schema = "public")
public class TimersSetting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id; // タイマー設定ID

	@Column(name = "focus_time")
	private String focusTime; // 集中時間
	
	@Column(name = "break_time")
	private String breakTime; // 休憩時間
	
	@Column(name = "registar_at")
	private LocalDateTime registarAt; // 登録日
	
	@Column(name = "user_id")
	private Long userId; // ユーザーID(外部キー)
	
	// アクセサメソッド
	
	// タイマー設定ID
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	// ユーザーID
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	// 集中時間
	public String getFocusTime() {
		return focusTime;
	}
	
	public void setFocusTime(String focusTime) {
		this.focusTime = focusTime;
	}
	
	
	// 休憩時間
	public String getBreakTime() {
		return breakTime;
	}
	
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
	
	
	// 登録日
	public LocalDateTime getRegistarAt() {
		return registarAt;
	}
	
	public void setRegistarAt(LocalDateTime registarAt) {
		this.registarAt = registarAt;
	}
	
}
