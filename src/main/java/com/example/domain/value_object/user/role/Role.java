package com.example.domain.value_object.user.role;

/**
 * 権限クラス
 */
public class Role {
    private final String role;

    public Role(String role) {
        inputRole(role);
        this.role = role;
    }


    /**
     * 入力された権限を設定する
     * 
     * @param value
     * @return 権限 ADMIN/GENERAL
     */
    private String inputRole(String value) {
        if (value.equals("ADMIN"))  return value = "ADMIN";   
        return value = "GENERAL";
    }
}
