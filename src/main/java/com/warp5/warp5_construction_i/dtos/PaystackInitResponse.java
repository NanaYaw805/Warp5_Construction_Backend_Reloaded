package com.warp5.warp5_construction_i.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaystackInitResponse {

    private boolean status;
    private String message;
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

        @JsonProperty("authorization_url")
        private String authorizationUrl;

        @JsonProperty("access_code")
        private String accessCode;

        private String reference;

        public String getAuthorizationUrl() {
            return authorizationUrl;
        }

        public String getAccessCode() {
            return accessCode;
        }

        public String getReference() {
            return reference;
        }
    }
}
