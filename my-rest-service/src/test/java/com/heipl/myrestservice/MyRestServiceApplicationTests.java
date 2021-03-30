package com.heipl.myrestservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MyRestServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	private static final String SPRING_COMMUNITY = "Spring Community";

	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/greeting"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {
		this.mockMvc.perform(get("/greeting").param("name", SPRING_COMMUNITY))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").value("Hello, " + SPRING_COMMUNITY + "!"));
	}

}