package com.sandeep.base.RoleBaseAuthorization.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Auth_Config {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin=User.builder()
				.username("sandeep")
				.password(passwordEncoder().encode("123456"))
				.roles("Admin").build();
		UserDetails user=User.builder()
				.username("Khushboo")
				.password(passwordEncoder().encode("123456"))
				.roles("User").build();
		
		return InMemoryUserDetailsManager(admin,user);
	}
	//Use For Me
	private UserDetailsService InMemoryUserDetailsManager(UserDetails admin, UserDetails user) {
		return null;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
	 return security.csrf(csrf->csrf.disable())
			 .authorizeHttpRequests(authorize->authorize
					 .requestMatchers(HttpMethod.GET).hasRole("Admin")
					 .requestMatchers(HttpMethod.POST).hasRole("User")
					 .requestMatchers(HttpMethod.PUT).hasRole("User")
					 .anyRequest()
					 .authenticated())
			 .httpBasic(Customizer.withDefaults()).build();
	}
}
