package com.ems.springboot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class JSONUtils {
    /**
     * Returns an {@link ObjectNode} with an {@link ArrayNode} as value.
     *
     * Created to prevent code repetition.
     *
     * @param key of the Array
     * @param value Array to be passed
     * @return {@link ObjectNode} which contains a node
     */
    public ObjectNode addArrayNode(String key, List<?> value) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.valueToTree(value);
        objectNode.putArray(key).addAll(arrayNode);
        return objectNode;
    }
}
