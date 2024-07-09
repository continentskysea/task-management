package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	 * タイマー情報検索機能
	 * 
	 * @param settingId タイマーID
	 * 
	 */
	public TimersSetting findById(Long settingId) {
		// DBから該当するIDを取得しタイマーリストを生成する
		Optional<TimersSetting> timsersSettingOptional = timersSettingRepository.findById(settingId);
		return timsersSettingOptional.orElse(null); // OptinalからTimersSettingオブジェクトを取得する
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
	public TimersSetting getUsersSettingTimer() {
		// ログインしているユーザー情報のIDを取得する
		Long userId = userService.getCurrentUserId();
		System.out.println(userId);
		return timersSettingRepository.findTopByUserIdOrderByIdDescFechFirstRowsOnlySetting(userId);
		
	}

	/**
	 * ユーザー別タイマー情報全件取得処理
	 * 
	 * @param Id ユーザーID
	 * @return ユーザー別登録タイマー情報リスト
	 */
	public List<TimersSetting> getTimersByUserId(Long userId) {
		return timersSettingRepository.findByUserId(userId);
	}

	/**
	 * IDに紐づくタイマー情報取得処理
	 * 
	 * @param id タイマーID
	 * @return タイマー情報
	 */
	public Optional<TimersSetting> get(Long id) {
		return timersSettingRepository.findById(id);
	}

	/**
	 * タイマー情報削除処理
	 * 
	 * @param id タイマーID
	 */
	public void delete(Long id) {
		timersSettingRepository.deleteById(id);
	}
}
