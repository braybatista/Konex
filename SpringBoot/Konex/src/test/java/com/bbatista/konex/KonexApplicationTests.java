package com.bbatista.konex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KonexApplication.class)
@WebAppConfiguration
public abstract class KonexApplicationTests {
	
	@Autowired
	WebApplicationContext wac;
	
	protected MockMvc mvc;
	
	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

}
