package com.github.llmjava.bard.request;

import com.github.llmjava.bard.BardSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenerationRequest {

    String snlm0e;
    BardSession session;
    String text;

    public GenerationRequest(Builder builder) {
        this.text = builder.text;
        this.snlm0e = builder.snlm0e;
        this.session = builder.session;
    }

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("bl", "boq_assistant-bard-web-server_20230713.13_p0");
        params.put("_reqid", session.nextReqId());
        params.put("rt", "c");
        return params;
    }

    public Map<String, String> getFormData() {
        Map<String, String> data = new HashMap<>();
        data.put("f.req", getFReq());
        data.put("at", snlm0e);
        return data;
    }

    public String getFReq() {
        String chatInfo = String.join("\\\", \\\"", Arrays.asList(session.conversation_id, session.response_id, session.choice_id));
        String input_text_struct = "[[\\\""+text+"\\\"], null, [\\\""+chatInfo+"\\\"]]";
        return "[null, \""+input_text_struct+"\"]";
    }

    public static class Builder {
        String snlm0e;
        BardSession session;
        String text;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }
        public Builder withSession(BardSession session) {
            this.session = session;
            return this;
        }
        public Builder withSNLM0e(String snlm0e) {
            this.snlm0e = snlm0e;
            return this;
        }

        public GenerationRequest build() {
            return new GenerationRequest(this);
        }
    }

}
