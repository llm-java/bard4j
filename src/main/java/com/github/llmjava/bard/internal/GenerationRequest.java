package com.github.llmjava.bard.internal;

import com.github.llmjava.bard.BardSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenerationRequest {

    BardSession session;

    public GenerationRequest(BardSession session) {
        this.session = session;
    }

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("bl", "boq_assistant-bard-web-server_20230713.13_p0");
        params.put("_reqid", session.getReqId());
        params.put("rt", "c");
        return params;
    }

    public Map<String, String> getFormData(String text, String snlm0e) {
        Map<String, String> data = new HashMap<>();
        String chatInfo = String.join("\\\",\\\"", Arrays.asList(session.conversation_id, session.response_id, session.choice_id));
        String input_text_struct = "[[\\\""+text+"\\\"], null, [\\\""+chatInfo+"\\\"]]";
        data.put("f.req", "[null, \""+input_text_struct+"\"]");
        data.put("at", snlm0e);
        return data;
    }
}
