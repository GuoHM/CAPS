package com.caps.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandle implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/welcome");
			return;
		} else if (roles.contains("ROLE_LECTURER")) {
			response.sendRedirect("/lecturer/lecturer-course");
			return;
		} else if (roles.contains("ROLE_STUDENT")) {
			response.sendRedirect("/student/student-course");
			return;
		} else {
			response.sendRedirect("/error");
		}

	}
}