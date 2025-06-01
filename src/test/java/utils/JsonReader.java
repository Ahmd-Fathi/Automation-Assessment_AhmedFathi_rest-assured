package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Post;
import java.io.File;

public class JsonReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Post readPostFromJson(String filePath, String key) throws Exception {
        File file = new File("src/test/resources/" + filePath);
        JsonNode rootNode = mapper.readTree(file);
        JsonNode postNode = rootNode.get(key);
        return mapper.treeToValue(postNode, Post.class);
    }
}