package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
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
	private final UserService userService;
	
	// コンストラクタインジェクション
	@Autowired
	public TimersSettingService(TimersSettingRepository timersSettingRepository, UserService userService) { 
		this.timersSettingRepository = timersSettingRepository;
		this.userService = userService;
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
		
		// LocalDateTimeオブジェクトを生成し、登録日時をセットする
		LocalDateTime registarAt = LocalDateTime.now();
		timersSetting.setRegistarAt(registarAt);		
		
		return timersSettingRepository.save(timersSetting);
	}
	
	/**
	 * ログインしているユーザーIDに紐づくタイマー設定テーブルの最新のデータを取得する
	 * 
	 * @return タイマー情報
	 */
	public TimersSetting getUsersFocusTimer() {
		// ログインしているユーザー情報のIDを取得する
		Integer userId = userService.getCurrentUserId();
		
		return timersSettingRepository.findTopByUserIdOrderByRegistarAtDesc(userId);
		
	}
}
