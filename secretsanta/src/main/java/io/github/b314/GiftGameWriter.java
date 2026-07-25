package io.github.b314;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class GiftGameWriter {

    public static void gameWriter(GiftGame game, File gameFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode gameRoot = objectMapper.createObjectNode();

        gameRoot.put("title", game.getTitle());

        ArrayNode playersRoot = gameRoot.putArray("players");
        for (Player player : game.getPlayers()) {
            ObjectNode playerNode = playersRoot.addObject();
            playerNode.put("name", player.getName());

            ArrayNode giftsNode = playerNode.putArray("gifts");
            for (String gift : player.getGifts()) {
                giftsNode.add(gift);
            }

            Player giftee = player.getAssigned(); 

            if (giftee != null) {
                playerNode.put("assignment", giftee.getName());
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(gameFile, gameRoot);
    }
}
