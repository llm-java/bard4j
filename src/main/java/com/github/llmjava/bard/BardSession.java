package com.github.llmjava.bard;

import java.util.UUID;

public class BardSession {

    public BardSession(String snlm0e) {
        this.snlm0e = snlm0e;
    }

    public String snlm0e;
    public String conversation_id;
    public String response_id;
    public String choice_id;

    public String getReqId() {
        return UUID.randomUUID().toString();
    }
}
