package com.github.llmjava.bard.internal;

import com.github.llmjava.bard.BardSession;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class GenerationRequestTest {

    @Test
    public void should_get_correct_params() {
        BardSession session = new BardSession("snlm0e");
        GenerationRequest request = new GenerationRequest(session);

        Map<String, String> params = request.getParams();
        assertThat(params).containsEntry("bl", "boq_assistant-bard-web-server_20230713.13_p0");
        assertThat(params).containsKey("_reqid");
        assertThat(params).containsEntry("rt", "c");
    }

    @Test
    public void should_get_correct_form_data() {
        BardSession session = new BardSession("snlm0e");
        GenerationRequest request = new GenerationRequest(session);

        Map<String, String> data = request.getFormData("hi", session.snlm0e);
        assertThat(data).containsEntry("f.req", "[null, \"[[\\\"hi\\\"], null, [\\\"null\\\",\\\"null\\\",\\\"null\\\"]]\"]");
        assertThat(data).containsEntry("at", session.snlm0e);
    }
}
