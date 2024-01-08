package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.TimerLogRepository;

/**
 * タイマーログサービスクラス
 * @author yoshi
 *
 */
@Service
public class TimerLogService {
	// タイマーログリポジトリのフィールドを追加
	private static TimerLogRepository timerLogRepository;
	
	// コンストラクタインジェクション
	@Autowired
	public TimerLogService(TimerLogRepository timerLogRepository) {
		this.timerLogRepository = timerLogRepository;
	}
}
