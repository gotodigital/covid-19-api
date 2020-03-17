package com.gotodigital.covid19.api.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
public class GeneralCOVID19Controller {

	

	@GetMapping("/articlesToday")
	@CrossOrigin
	public ResponseEntity<String>  getArticlesToday() throws ParseException {
		
		LocalDate date = LocalDate.now();
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("paramters");
		ResponseEntity<String> articles = rest.exchange("http://newsapi.org/v2/everything?q=coronavirus&language=es&sortBy=popularity&from=" + date + "&apiKey=50ea6b89e0ab476d8659d7ded64aa5b2", HttpMethod.GET, entity,
				String.class);
		return articles;
	}
	
	@GetMapping("/situationByCountry/{countryName}")
	@CrossOrigin
	public JsonNode casesByCountry(@PathVariable(value = "countryName") String countryName) throws ParseException, JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("paramters");
		ResponseEntity<String> world = rest.exchange("https://pomber.github.io/covid19/timeseries.json", HttpMethod.GET, entity,
				String.class);
		
		JsonNode jsonNode = mapper.readTree(world.getBody());
		
		JsonNode byCountry = jsonNode.get(countryName);
		
		return byCountry;

	}
	
	@GetMapping("/situationByCountry/all")
	@CrossOrigin
	public JsonNode casesByAllCountries() throws ParseException, JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("paramters");
		ResponseEntity<String> world = rest.exchange("https://pomber.github.io/covid19/timeseries.json", HttpMethod.GET, entity,
				String.class);
		return mapper.readTree(world.getBody());

	}
	
	@GetMapping("/principalNewsPortalByLanguage/{lang}")
	@CrossOrigin
	public ResponseEntity<String> newsPortalByLang(@PathVariable(value = "lang") String lang) throws ParseException, JsonMappingException, JsonProcessingException {
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("paramters");
		ResponseEntity<String> world = rest.exchange("http://newsapi.org/v2/sources?language=" + lang + "&apiKey=50ea6b89e0ab476d8659d7ded64aa5b2", HttpMethod.GET, entity,
				String.class);
		return world;

	}
	
	@GetMapping("/globalInfo/")
	@CrossOrigin
	public ResponseEntity<String> globalInformation() throws ParseException, JsonMappingException, JsonProcessingException, URISyntaxException {
		URI global = new URI("https://corona.lmao.ninja/all");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(global);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@GetMapping("/general")
	@CrossOrigin
	public ResponseEntity<String> infoAndRecomend() throws ParseException, JsonMappingException, JsonProcessingException {
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("paramters");
		ResponseEntity<String> world = rest.exchange("https://coronavirus.epidemixs.org/assets/assets/data_es.json", HttpMethod.GET, entity,
				String.class);
		return world;
	}
	
	@GetMapping("/nearPharmacies/lat/{latitude}/long/{longitude}")
	@CrossOrigin
	public String nearPharmacies(@PathVariable(value = "latitude") double lat, @PathVariable(value = "longitude") double lon) throws ParseException, JsonMappingException, JsonProcessingException {
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("paramters");
		ResponseEntity<String> world = rest.exchange("https://places.sit.ls.hereapi.com/places/v1/autosuggest?at=" + lat + "," + lon +"&q=farmacia&apiKey=8Elv2s1gbsZRB9y0OIyXWf9y_IMYxMyJEc_ThffbIF4", HttpMethod.GET, entity,
				String.class);
		return world.getBody();
	}
	
	

	
}
