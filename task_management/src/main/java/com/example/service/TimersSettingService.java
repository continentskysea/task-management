package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.TimersSetting;
import com.example.repository.TimersSettingRepository;

/**
 * タイマー設定用サービスクラス
 * @author yoshi
 *
 */
@Service
public class TimersSettingService {
	// タイマー設定用リポジトリのフィールドを追加
	private final TimersSettingRepository timersSettingRepository;
	
	// コンストラクタインジェクション
	public TimersSettingService(TimersSettingRepository timersSettingRepository) {
		this.timersSettingRepository = timersSettingRepository;
	}
	
	/**
	 * タイマー登録機能
	 * 
	 * @param focusTime 保存したい集中時間情報
	 * @return 保存した集中時間情報
	 */
	public TimersSetting save(TimersSetting timersSetting) {
		return timersSettingRepository.save(timersSetting);
	}
}
