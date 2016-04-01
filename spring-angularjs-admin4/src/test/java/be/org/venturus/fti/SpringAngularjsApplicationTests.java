package be.org.venturus.fti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import be.org.venturus.fti.config.AdminApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminApplication.class)
@WebAppConfiguration
public class SpringAngularjsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
