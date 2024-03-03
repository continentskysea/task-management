package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.TimerLog;
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
	
	/**
	 * タイマーログ登録処理
	 * @param timerLog 保存したいログ情報
	 * @return 登録したログ情報
	 */
	public TimerLog save(TimerLog timerLog) {
		return timerLogRepository.save(timerLog);
	}
	
}
