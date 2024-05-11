package com.example.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationSuccess (HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
		String taregetUrl = determinTargetUrl(auth);
		redirectStrategy.sendRedirect(req, res, taregetUrl);
	}
	
	protected String determinTargetUrl(Authentication auth) {
		String url = "";
		// ユーザーのロールに基づきリダイレクトURLを設定
		for (GrantedAuthority authority : auth.getAuthorities()) {
			String roleName = authority.getAuthority();
			System.out.println(roleName);
			if (roleName.equals("ADMIN")) {
				System.out.println(authority.getAuthority());
				url = "/getAdminHome";
				return url;
			}
			
		}
		url = "/home";
		return url;
	}
	
	
}
