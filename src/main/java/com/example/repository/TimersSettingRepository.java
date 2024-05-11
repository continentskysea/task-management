package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.TimersSetting;
import java.util.List;


/**
 * タイマー設定用リポジトリインターフェース
 * @author yoshi
 *
 */
public interface TimersSettingRepository extends JpaRepository<TimersSetting, Long>{
	// ログインしているユーザーID情報から登録されているタイマーをエンティティの最新のレコードを取得する
	@Query("SELECT ts FROM TimersSetting ts WHERE ts.userId = ?1 ORDER BY ts.id DESC")
	public TimersSetting findTopByUserIdOrderByIdDesc(Long currentUserId);

	// ログインしているユーザーID情報から登録されているタイマーをエンティティのリストを取得する
	public  List<TimersSetting> findByUserId(Long userId);
}
