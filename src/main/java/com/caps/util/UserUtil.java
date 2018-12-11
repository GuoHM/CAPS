package com.caps.util;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {
	public static String currentUser(HttpSession session) {
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		return ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
	}
}
