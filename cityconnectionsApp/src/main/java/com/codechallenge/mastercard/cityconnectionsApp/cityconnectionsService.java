package com.codechallenge.mastercard.cityconnectionsApp;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class cityconnectionsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(cityconnectionsService.class);
	
	@Value ("${connectedcitiesfilepath}")
	private String path;
	
	private Map<String,Set<String>> adjmap= new HashMap<>();
	@PostConstruct
	public void init() {
		List<String> citypairs = new ArrayList<>();

		try {
			citypairs = Files
					.readAllLines(ResourceUtils.getFile("classpath:" + path).toPath());
		} catch (IOException err) {
			LOGGER.error("File not found",this.path);
			LOGGER.error(err.toString());
		}

		
		for (String c : citypairs) {
			if(c!=null && c.length()>0 ) {
				String[] cities=c.split(",");
				if(cities.length==2) {
					adjacent_cities_map(cities[0],cities[1]);
				}
				else {
					throw new IllegalArgumentException();
				}
			}
		}
	
	}
	public String iscitiesconnected(String origin, String des) {
		origin=origin.trim().toLowerCase();
		des=des.trim().toLowerCase();
		if(adjmap.containsKey(origin) && adjmap.containsKey(des)) {
			Set<String> visited = new HashSet<>();
			Queue<String> q = new LinkedList<>();
			q.offer(origin);

			while (!q.isEmpty()) {
				String curcity = q.poll();
				visited.add(curcity);

				if (curcity.equals(des)) {
					return "yes";
				}

				for (String neighbor : adjmap.get(curcity)) {
					if (!visited.contains(neighbor)) {
						q.offer(neighbor);
					}
				}
		   }
		}	
		return "no";
	}
	
	private void adjacent_cities_map(String c1, String c2) {
		c1=c1.trim().toLowerCase();
		c2=c2.trim().toLowerCase();
		adjmap.computeIfAbsent(c1, k -> new HashSet<String>()).add(c2);
		adjmap.computeIfAbsent(c2, k -> new HashSet<String>()).add(c1);
	}

}
