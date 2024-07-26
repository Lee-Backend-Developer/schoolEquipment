package com.equipment.school_equipment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface JsonUtill {

    default String objectToString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    default MappingObj stringToObject(String value)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(value, MappingObj.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class MappingObj {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private String expires_in;
        private String scope;
        private String refresh_token_expires_in;

    }
}
