package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.TimersSetting;

/**
 * タイマー設定用リポジトリインターフェース
 * @author yoshi
 *
 */
public interface TimersSettingRepository extends JpaRepository<TimersSetting, Integer>{
	// ログインしているユーザーID情報から登録されているタイマーをエンティティの最新のレコードを取得する
	public TimersSetting findTopByUserIdOrderByIdDesc(Integer currentUserId);
}
