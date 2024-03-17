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
 * タイマーログ用のデータクラス
 * @author yoshi
 *
 */
@Entity
@Table(name = "TIMER_LOGS")
public class TimerLog {
	@Id
	@SequenceGenerator(name = "TIMER_LOGS_ID_GENERATOR", sequenceName = "TIMER_LOGS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIMER_LOGS_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id; // タイマーログID
	
	@Column(name = "SETTING_ID")
	private Integer settingId; // タイマー設定ID(外部キー)
	
	@Column(name = "START_TIME")
	private LocalDateTime startTime; // 開始時間
	
	@Column(name = "END_TIME")
	private LocalDateTime endTime; // 終了時間
	
	@Column(name = "TIMER_TYPE")
	private String timerType; // タイマーのタイプ(集中 or 休憩)
	
	// アクセサメソッド
	
	// タイマーログID
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	// タイマー設定ID
	public Integer getSettingId() {
		return settingId;
	}
	
	public void setSettingId(Integer settingId) {
		this.settingId = settingId;
	}
	
	
	// 開始時間
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	
	// 終了時間
	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	
	// タイマーの種類
	public String getTimerType() {
		return timerType;
	}
	
	public void setTimerType(String timerType) {
		this.timerType = timerType;
	}

}
