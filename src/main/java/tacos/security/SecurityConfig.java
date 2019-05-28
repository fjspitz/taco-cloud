package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/design", "/orders", "/console/**")
			.hasRole("USER")
			.antMatchers("/", "/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
			.and()
				.logout()
					.logoutSuccessUrl("/");
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}
}
