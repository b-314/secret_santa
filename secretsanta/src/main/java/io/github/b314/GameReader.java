package io.github.b314;

import java.io.File; 
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; 

public class GameReader {
    public static Game gameReader(File gameFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(); 
        JsonNode gameRoot = objectMapper.readTree(gameFile); 
        Game game = new Game(getText(gameRoot, "title"));
        JsonNode playersRoot = gameRoot.path("players");
        for(JsonNode playerNode : playersRoot) {
            try {
                game.addPlayer(getText(playerNode, "name")); 
                try {
                    JsonNode giftsNode = getArray(playerNode, "gifts"); 
                    for(JsonNode gift : giftsNode) {
                        if(gift != null && gift.isTextual() && !"".equals(gift.asText())) {
                            game.addGift(gift.asText()); 
                        }
                    }
                }
                catch (IllegalArgumentException e) {
                }
            }
            catch (IllegalArgumentException e) {
            }
        }
        return game;
    }

    private static String getText(JsonNode node, String field) {
        JsonNode val = node.get(field); 
        if(val == null || !val.isTextual() || "".equals(val.asText())) {
            throw new IllegalArgumentException("Value must be an non-empty string"); 
        }
        return val.asText(); 
    }

    private static JsonNode getArray(JsonNode node, String field) {
        JsonNode val = node.get(field); 
        if(val == null || !val.isArray()) {
            throw new IllegalArgumentException("Value must be an array"); 
        }
        return val; 
    }
}