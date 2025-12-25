package io.github.b314;

import com.fasterxml.jackson.databind.JsonNode; 
import com.fasterxml.jackson.databind.ObjectMapper; 
import java.io.File; 
import java.io.IOException; 

public class GameReader {
    public Game gameReader(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); 
        JsonNode jsonNode = objectMapper.readTree(new File(fileName)); 
    }
}
