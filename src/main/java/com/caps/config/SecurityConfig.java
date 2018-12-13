package com.caps.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select userid, password, enabled from account where userid=?")
		.authoritiesByUsernameQuery("select userid, authority " + "from accountrole where userid=?")
		.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
		// .and()
		// .httpBasic(); // Authenticate users with HTTP basic authentication
		http.authorizeRequests()
		.antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/lecturer/*").access("hasRole('ROLE_LECTURER')")
		.antMatchers("/student/*").access("hasRole('ROLE_STUDENT')")
		.and()
		.formLogin().loginPage("/login")
		.successHandler(new LoginSuccessHandle())
		.failureUrl("/login?error")
		.usernameParameter("userid").passwordParameter("password")
		.and()
		.exceptionHandling().accessDeniedPage("/error")
		.and()
		.csrf().disable();
	}

}
