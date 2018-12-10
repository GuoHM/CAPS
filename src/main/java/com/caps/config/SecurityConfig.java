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
		.usersByUsernameQuery("select username, password, enabled"
				+ " from users where username=?")
		.authoritiesByUsernameQuery("select username, authority "
				+ "from authorities where username=?")
		.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//    http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
		//    .and()
		//    .httpBasic(); // Authenticate users with HTTP basic authentication
		http.authorizeRequests()
		.antMatchers("/CustomersJPA/admin").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/CustomersJPA/dba").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
		.and()
		.formLogin().loginPage("/CustomersJPA/login")
		.defaultSuccessUrl("/CustomersJPA/InsertCustomer").failureUrl("/CustomersJPA/login?error")
		.usernameParameter("userid").passwordParameter("password")
		.and()
		.csrf().disable();
	}
}


