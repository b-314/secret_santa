package io.github.b314;

import java.io.File; 
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; 

public class GameReader {
    public static Game gameReader(File gameFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); 
        JsonNode gameRoot = objectMapper.readTree(gameFile); 
        Game game = new Game(gameRoot.path("title").asText());
        JsonNode playersRoot = gameRoot.path("players");
        for(JsonNode playerNode : playersRoot) {
            game.addPlayer(playerNode.path("name").asText()); 
            JsonNode giftsNode = playerNode.path("gifts"); 
            for(JsonNode gift : giftsNode) {
                game.addGift(gift.asText()); 
            } 
        }
        return game;
    }
}