package be.org.venturus.fti.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import br.org.venturus.fti.filter.CsrfHeaderFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class UiApplication {
	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends OAuth2SsoConfigurerAdapter {
		
		@Override
	    public void match(RequestMatchers matchers) {
	      matchers.anyRequest();
	    }
		
		@Override
        public void configure(HttpSecurity http) throws Exception {
			http.logout().and().authorizeRequests().antMatchers("/index.html", "/home.html", "/", "/")
	          .permitAll()
	          .anyRequest()
	          .authenticated()
	          .and()
	          .csrf()
	          .csrfTokenRepository(csrfTokenRepository())
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
