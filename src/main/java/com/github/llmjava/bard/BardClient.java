package com.github.llmjava.bard;

import com.github.llmjava.bard.internal.ResponseParser;
import retrofit2.Response;

import java.io.IOException;

public class BardClient {
    private final BardApi api;
    private final BardConfig config;

    BardClient(Builder builder) {
        this.api = builder.api;
        this.config = builder.config;
    }

    public String getSNlM0e() {
        try {
            Response<String> response = api.getSNlM0e().execute();
            if (response.isSuccessful()) {
                String body = response.body();
                return ResponseParser.getSNlM0e(body);
            } else  {
                throw newException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static RuntimeException newException(retrofit2.Response<?> response) throws IOException {

        int code = response.code();
        String body = response.errorBody().string();
        String errorMessage = String.format("status code: %s; body: %s", code, body);
        return new RuntimeException(errorMessage);
    }


    public static class Builder {
        private BardApi api;
        private BardConfig config;

        public Builder withConfig(BardConfig config) {
            this.config = config;
            this.api = new BardApiFactory().build(config);
            return this;
        }

        public BardClient build() {
            return new BardClient(this);
        }
    }
}
