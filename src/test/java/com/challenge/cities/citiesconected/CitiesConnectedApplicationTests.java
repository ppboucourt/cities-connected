package com.challenge.cities.citiesconected;

import com.challenge.cities.citiesconected.util.ReadFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitiesConnectedApplicationTests {

	@Autowired
	private final ResourceLoader resourceLoader;

	@Autowired
	private final ReadFile readFile;

	public CitiesConnectedApplicationTests(ResourceLoader resourceLoader, ReadFile readFile) {
		this.resourceLoader = resourceLoader;
		this.readFile = readFile;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void loadFile() throws IOException {
		System.out.println("Loading file...");
		Resource resource =  resourceLoader.getResource("classpath:docs/cities.txt");
		InputStream input = resource.getInputStream();
		String[] expected = {"Boston, New York", "Philadelphia, Newark", "Newark, Boston", "Trenton, Albany"};
		String[] data = readFile.readFromInputStream(input).split("\n");
		Assert.assertArrayEquals(expected, data);
	}

}

