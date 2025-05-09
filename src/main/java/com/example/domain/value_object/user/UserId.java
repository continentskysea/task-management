package com.example.domain.value_object.user;

/**
 * ユーザーID値オブジェクトクラス
 */
public class UserId {
    private final int userId;
    
    UserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

}
