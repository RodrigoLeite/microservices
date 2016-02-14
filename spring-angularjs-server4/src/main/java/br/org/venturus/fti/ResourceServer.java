package br.org.venturus.fti;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
//@RequestMapping(value = "/resource")
public class ResourceServer {
	
	private String message = "Hello world";
	private List<Change> changes = new ArrayList<>();

	@RequestMapping(value="/", method=RequestMethod.GET)
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", message);
		return model;
	}
	
	@RequestMapping(value="/changes", method=RequestMethod.GET)
	public List<Change> changes() {
		return changes;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Map<String, Object> update(@RequestBody Map<String,String> map, Principal principal) {
		if (map.containsKey("content")) {
			message = map.get("content");
			Change change = new Change();
			change.setTimestamp(new Date());
			change.setUser(principal.getName());
			change.setContent(message);
			changes.add(change);
			System.out.println(changes);
			if (changes.size()>10) {
				changes = new ArrayList<>();
			}
		}
		return home();
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceServer.class, args);
	}
}