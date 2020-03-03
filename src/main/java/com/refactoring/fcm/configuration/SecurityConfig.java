package com.refactoring.fcm.configuration;

import com.refactoring.fcm.handler.SaveLoginIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	SaveLoginIdHandler slh;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/login", "/service", "/resources/**", "/view", "/mail").permitAll()
				.antMatchers("/admin/**", "/create").hasRole("ADMIN")
				.antMatchers("/manager/**").hasRole("MANAGER")
				.antMatchers("/trainer/**").hasRole("TRAINER")
				.antMatchers("/member/**").hasRole("MEMBER")
				.anyRequest().authenticated()
                .antMatchers("/**").permitAll()
                .and()

           .formLogin()
           		.successHandler(slh)
           		.loginPage("/login")
           		.loginProcessingUrl("/loginProcess")
                .permitAll()
                .and()
           .logout()
           		.logoutSuccessUrl("/login")
           		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();


    }

	@Override
	 public void configure(WebSecurity web) throws Exception {
		//spring security 제외 경로설정
		web.ignoring().antMatchers("/resources/**").antMatchers("/api/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
