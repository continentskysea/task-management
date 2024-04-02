package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.TimersSetting;
import java.util.List;


/**
 * タイマー設定用リポジトリインターフェース
 * @author yoshi
 *
 */
public interface TimersSettingRepository extends JpaRepository<TimersSetting, Integer>{
	// ログインしているユーザーID情報から登録されているタイマーをエンティティの最新のレコードを取得する
	public TimersSetting findTopByUserIdOrderByIdDesc(Integer currentUserId);

	// ログインしているユーザーID情報から登録されているタイマーをエンティティのリストを取得する
	public  List<TimersSetting> findByUserId(Integer userId);
}
