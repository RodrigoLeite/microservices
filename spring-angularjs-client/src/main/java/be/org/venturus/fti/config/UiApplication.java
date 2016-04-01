package be.org.venturus.fti.config;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.venturus.fti.filter.CsrfHeaderFilter;

@SpringBootApplication
@RestController
@EnableZuulProxy
@EnableRedisHttpSession
public class UiApplication {
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/index.html", "/home.html", "/login.html", "/")
			.permitAll().anyRequest().authenticated()
			.and()
			.csrf().csrfTokenRepository(csrfTokenRepository())
			.and()
			.logout()
			.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
		}
		
		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}
	}

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }
}
