// package com.example.domain.value_object;

// import org.junit.jupiter.api.Test;
// import com.example.domain.value_object.user.UserName;

// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// public class TestUserName {
    
//     @Test
//     public void testUserName() {
//         // 失敗ケース1 空文字入力
//         UserName userName = new UserName("");
//         // userName.getUserNameValue();

//         // 失敗ケース2 表記ミス
//         UserName userName2 = new UserName("123456") ;
//         // userName2.getUserNameValue();

//         // 失敗ケース2 3文字未満
//         UserName userName3 = new UserName("田中");
//         // userName3.getUserNameValue();

//         // 失敗ケース4 12文字より多い
//         UserName userName4 = new UserName("田中太郎太郎太郎太郎太郎太");
//         // userName4.getUserNameValue();

//         // 成功ケース1 入力あり 且つ 漢字表記 且つ 3文字以上12文字以下
//         UserName userName5 = new UserName("田中太郎");
//         // userName5.getUserNameValue();

//         // 成功ケース2 入力あり 且つ 漢字表記 且つ 3文字以上11文字
//         UserName userName6 = new UserName("金田太郎金田太郎金田太");
//         // userName6.getUserNameValue();

//         // 成功ケース2 入力あり 且つ 漢字表記 且つ 3文字以上12文字
//         UserName userName7 = new UserName("石田太郎石田太郎石田太郎");
//         // userName7.getUserNameValue();
//     }
// }
