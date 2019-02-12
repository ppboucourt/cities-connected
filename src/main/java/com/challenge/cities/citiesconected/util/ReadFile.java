package com.challenge.cities.citiesconected.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class ReadFile {

    private final ResourceLoader resourceLoader;


    @Autowired
    public ReadFile(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public List<String> loadFile() throws IOException {
        System.out.println("Loading file...");
        Resource resource =  resourceLoader.getResource("classpath:docs/cities.txt");
        InputStream input = resource.getInputStream();
        String[] data = readFromInputStream(input).split("\n");
//        System.out.println(Arrays.toString(data));
        return Arrays.asList(data);
    }
}
