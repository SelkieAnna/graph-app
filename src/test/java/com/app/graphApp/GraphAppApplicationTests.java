package com.app.graphApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@ContextConfiguration(classes = GraphAppApplication.class)
@WebAppConfiguration
class GraphAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
