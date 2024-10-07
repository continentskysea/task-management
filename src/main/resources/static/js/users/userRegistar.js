/**
 * 登録の操作
 */
$(document).ready(function () {

    // 登録データを送信する
    $(".registarUser").on("click", function () {
        // htmlに入力された値を格納する
        let name = $("#nameInput").val();
        let email = $("#mailInput").val();
        let password = $("#passwordInput").val();

        // 入力データをオブジェクトに格納
        let user = {
          name: name,
          email: email,
          password: password,
        };

        // フォームの値をセットする
        $("#userFormContainer input[ name = 'name' ]").val(user.name);
        $("#userFormContainer input[ name = 'email' ]").val(user.email);
        $("#userFormContainer input[ name = 'password' ]").val(user.password);

        // フォームを送信
        $("#userLoginForm").submit();
    });
});