package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.TimersSetting;


/**
 * タイマー設定用リポジトリインターフェース
 * @author yoshi
 *
 */
public interface TimersSettingRepository extends JpaRepository<TimersSetting, Long>{
	// ログインしているユーザーID情報から登録されているタイマーをエンティティの最新のレコードを取得する
	// @Query("SELECT ts FROM TimersSetting ts WHERE ts.userId = ?1 ORDER BY ts.id DESC")
	@Query("SELECT ts FROM TimersSetting ts WHERE ts.userId = ?1 ORDER BY registarAt DESC FETCH FIRST 1 ROWS ONLY")
	public TimersSetting findTopByUserIdOrderByIdDescFechFirstRowsOnlySetting(Long currentUserId);

	// ログインしているユーザーID情報から登録されているタイマーをエンティティのリストを取得する
	public  List<TimersSetting> findByUserId(Long userId);
}
