package com.example.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.service.LoginUserService;

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
	
	private final LoginUserService loginUserService;
	
	@Autowired
	public SecurityConfig(LoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	protected SecurityFilterChain securityFilterChainConfig(HttpSecurity http) throws Exception {

		// ログイン処理
		http.formLogin(login -> login
				.loginProcessingUrl("/loginForm") // ログイン処理のパス
				.loginPage("/loginForm") // ログインページの指定
				.usernameParameter("email") // ログインページのメールアドレス
				.passwordParameter("password") // ログインページのパスワード
				.defaultSuccessUrl("/home") // ログイン成功時のデフォルトの表示
				.successHandler(new AuthenticationSuccessHandlerImpl())
				.failureUrl("/loginForm?error") // ログイン失敗時のパス
		);
		// ログアウト処理
		http.logout(logout -> logout
				.logoutUrl("/logout") // ログアウト処理のパス
				.logoutSuccessUrl("/loginForm") // ログアウト成功時のパス
		
		);
		return http.build();
	}
	
	/**
	 * ハッシュアルゴリズムの定義
	 * 
	 * @return 生成されたパスワードのハッシュ値
	 */
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/**
	 * Webシステム全体のセキュリティ設定のカスタマイズ
	 * 
	 * @return
	 */
	@Bean
	protected WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/css/**").requestMatchers("/js/**");
	}
}
