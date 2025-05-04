package com.example.domain.value_object.user.name;
import com.example.domain.shared.ValidationDomainException;

/**
 * 苗字形式例外クラス
 */
public class LastNameInputFormatException extends ValidationDomainException {
    public LastNameInputFormatException() {
        super("名前は漢字またはカタカナで入力したください。");
    }
}
