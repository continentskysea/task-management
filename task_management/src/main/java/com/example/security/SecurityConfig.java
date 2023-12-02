package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * セキュリティ設定クラス
 * @author yoshi
 *
 */
// Configuration を明示
@Configuration
// Spring Security の設定を有効化する
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	protected SecurityFilterChain securityFilterChainConfig(HttpSecurity http) throws Exception {
		// ログイン処理
		http.formLogin(login -> login
				.loginProcessingUrl("/login") // ログイン処理のパス
				.loginPage("/loginForm") // ログインページの指定
				.usernameParameter("email") // ログインページのメールアドレス
				.passwordParameter("password") // ログインページのパスワード
				.defaultSuccessUrl("/home", true) // ログイン成功時のパス
				.failureUrl("/loginForm?error") // ログイン失敗時のパス
				.permitAll()
		// ログアウト処理		
		).logout(logout -> logout
				.logoutUrl("/logout") // ログアウト処理のパス
				.logoutSuccessUrl("loginForm") // ログアウト成功時のパス
		);
		
		return http.build();
	}
}
