package com.codechallenge.mastercard.cityconnectionsApp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.codechallenge.mastercard.cityconnectionsApp.rest_controller.cityconnectionsController;

@WebMvcTest(cityconnectionsController.class)
@ComponentScan("com.codechallenge.mastercard.cityconnectionsApp")
@AutoConfigureMockMvc
public class cityconnectionsControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private cityconnectionsService service;

	@Test
	public void test_isconnected_cities_R_directlyconnected() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders
			    .get("/connected")
				.param("origin", "New York")
				.param("destination", "Boston")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("yes")));
	}
	
	@Test
	public void test_isconnected_cities_R_notconnected_butcitiesarepresent() throws Exception {
		mvc.perform(MockMvcRequestBuilders
						.get("/connected").param("origin", "Philadelphia")
						.param("destination", "Albany")
						.contentType(MediaType.APPLICATION_JSON))						
						.andDo(MockMvcResultHandlers.print())
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("no")));
	}

	@Test
	public void test_isconnected_cities_R_notconnected_cities_R_notpresent() throws Exception {
		mvc.perform(MockMvcRequestBuilders
						.get("/connected")
						.param("origin", "Mumbai")
						.param("destination", "Chennai")
						.contentType(MediaType.APPLICATION_JSON))
						.andDo(MockMvcResultHandlers.print())
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("no")));
	}
}
