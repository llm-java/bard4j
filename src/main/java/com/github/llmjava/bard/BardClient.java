package com.github.llmjava.bard;

import com.github.llmjava.bard.internal.ResponseParser;
import com.github.llmjava.bard.request.GenerationRequest;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

public class BardClient {
    private final BardApi api;
    private final BardConfig config;

    private final BardSession session;

    BardClient(Builder builder) {
        this.api = builder.api;
        this.config = builder.config;
        this.session = builder.session;
    }

    public String generate(String text) {
        try {
            GenerationRequest request = new GenerationRequest.Builder()
                    .withSession(session)
                    .withSNLM0e(getSNlM0e())
                    .withText(text)
                    .build();
            Map<String, String> params = request.getParams();
            Map<String, String> data = request.getFormData();
            Response<String> response = api.generate(params, data).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else  {
                throw newException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String requestSNlM0e() {
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

    public String getSNlM0e() {
        if(this.session.snlm0e == null) {
            this.session.snlm0e = config.getSNlM0e();
        }
        if(this.session.snlm0e == null) {
            this.session.snlm0e = requestSNlM0e();
        }
        System.out.println("snlm0e: " + session.snlm0e);
        return this.session.snlm0e;
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
        private BardSession session;

        public Builder withConfig(BardConfig config) {
            this.config = config;
            this.api = new BardApiFactory().build(config);
            this.session = new BardSession(config.getSNlM0e());
            return this;
        }

        public BardClient build() {
            return new BardClient(this);
        }
    }
}
