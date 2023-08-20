package com.github.llmjava.bard;

import java.util.Random;

public class BardSession {

    public BardSession(String snlm0e) {
        this.snlm0e = snlm0e;
    }

    public String snlm0e;
    public String conversation_id = "";
    public String response_id = "";
    public String choice_id = "";

    static Random generator = new Random();

    private Long _reqId = 0L;

    public String nextReqId() {
        _reqId += generator.nextInt(10000);
        return String.valueOf(_reqId);
    }
}
