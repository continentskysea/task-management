package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * タイマー設定用のデータクラス
 * @author yoshi
 *
 */
@Entity
@Table(name = "TIMERS_SETTINGS")
public class TimersSetting {
	@Id
	@SequenceGenerator(name = "TIMERS_SETTINGS_ID_GENERATOR", sequenceName = "TIMERS_SETTINGS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIMERS_SETTINGS_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id; // タイマー設定ID
	
	@Column(name = "USER_ID")
	private Integer userId; // ユーザーID(外部キー)
	
	@Column(name = "FOCUS_TIME")
	private String focusTime; // 集中時間
	
	@Column(name = "BREAK_TIME")
	private String breakTime; // 休憩時間
	
	@Column(name = "REGISTAR_AT")
	private LocalDateTime registarAt; // 登録日
	
	
	// アクセサメソッド
	
	// タイマー設定ID
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	// ユーザーID
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
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
