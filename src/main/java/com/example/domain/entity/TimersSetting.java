package com.example.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.example.domain.value_object.timer.BreakTime;
import com.example.domain.value_object.timer.FocusTime;
import com.example.domain.value_object.timer.TimerId;
import com.example.domain.value_object.user.UserId;
import com.example.domain.value_object.timer.RegistarAt;

/**
 * タイマー情報集約エンティティ
 * @author yoshi
 *
 */
@Entity
@Table(name = "timers_settings", schema = "verification")
public class TimersSetting {

    /**
     * メンバフィールド
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final TimerId id;

    @Column(name = "focus_time")
    private final FocusTime focusTime;

    @Column(name = "break_time")
    private final BreakTime breakTime;
    
    @Column(name = "user_id")
    private final UserId userId;

	@Column(name = "registar_at")
    private final RegistarAt


    /**
     * コンストラクタ
     */
    public TimersSetting(
            TimerId id,
            FocusTime focusTime,
            BreakTime breakTime,
            UserId userId,
            RegistarAt registarAt
    ) {
        this.id = id;
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        this.userId = userId;
        this.registarAt = registarAt; 
    }


    /**
     * オブジェクト生成
     * @return new Task(id, focusTime, breakTime, userId, registarAt);
     */
    public TimersSetting getTimersSetting() {
        return new TimersSetting(id, focusTime, breakTime, userId, registarAt);
    }

}
