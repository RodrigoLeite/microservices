package be.org.venturus.fti.config;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class UiApplication {

	@RequestMapping("/user")
	public Map<String, String> user(Principal user) {
		return Collections.singletonMap("name", user.getName());
	}

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
}
