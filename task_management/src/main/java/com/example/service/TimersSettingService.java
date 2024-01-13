package com.example.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.entity.TimersSetting;
import com.example.entity.User;
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
		// ログインしているユーザー情報をtimersSettingにセットする
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUser loginUser = (LoginUser)authentication.getPrincipal();
		User user = loginUser.getUser();
		timersSetting.setUserId(user.getId()); 
		
		return timersSettingRepository.save(timersSetting);
	}
	
	/**
	 * IDに紐づく設定タイマー情報取得
	 * 
	 * @param id タイマー設定ID
	 * @return 設定したタイマー情報
	 */
	public TimersSetting get(Integer id) {
		return timersSettingRepository.findById(id).get();
	}
}
