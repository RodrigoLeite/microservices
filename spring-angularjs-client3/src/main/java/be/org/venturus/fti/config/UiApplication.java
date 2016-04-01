package be.org.venturus.fti.config;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;

import br.org.venturus.fti.filter.CsrfHeaderFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class UiApplication extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.logout().and().antMatcher("/**").authorizeRequests().antMatchers("/index.html", "/home.html", "/", "/login").permitAll().anyRequest().authenticated().and().csrf().csrfTokenRepository(csrfTokenRepository()).and()
		                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
}

//Remove this when upgrading to Spring Boot 1.3.1 (https://github.com/spring-projects/spring-boot/issues/4553)
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class WorkaroundRestTemplateCustomizer implements UserInfoRestTemplateCustomizer {

	@Override
	public void customize(OAuth2RestTemplate template) {
		template.setInterceptors(new ArrayList<>(template.getInterceptors()));
	}
	
}
