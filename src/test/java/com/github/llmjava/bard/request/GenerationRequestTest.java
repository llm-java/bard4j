package com.github.llmjava.bard.request;

import com.github.llmjava.bard.BardSession;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class GenerationRequestTest {

    @Test
    public void should_get_correct_params() {
        BardSession session = new BardSession("snlm0e");
        GenerationRequest request = new GenerationRequest.Builder().withSession(session).build();

        Map<String, String> params = request.getParams();
        assertThat(params).containsEntry("bl", "boq_assistant-bard-web-server_20230713.13_p0");
        assertThat(params).containsKey("_reqid");
        assertThat(params).containsEntry("rt", "c");
    }

    @Test
    public void should_get_correct_form_data() {
        BardSession session = new BardSession("snlm0e");
        GenerationRequest request = new GenerationRequest.Builder()
                .withSNLM0e(session.snlm0e)
                .withSession(session)
                .withText("hi")
                .build();

        Map<String, String> data = request.getFormData();
        assertThat(data).containsEntry("f.req", "[null, \"[[\\\"hi\\\"], null, [\\\"\\\", \\\"\\\", \\\"\\\"]]\"]");
        assertThat(data).containsEntry("at", session.snlm0e);
    }

    @Test
    public void should_create_correct_f_req() throws UnsupportedEncodingException {
        BardSession session = new BardSession("snlm0e");
        GenerationRequest request = new GenerationRequest.Builder()
                .withSNLM0e(session.snlm0e)
                .withSession(session)
                .withText("tell a joke")
                .build();
        String freq = request.getFReq();
        String expected = "%5Bnull%2C+%22%5B%5B%5C%22tell+a+joke%5C%22%5D%2C+null%2C+%5B%5C%22%5C%22%2C+%5C%22%5C%22%2C+%5C%22%5C%22%5D%5D%22%5D";
        assertThat(freq).isEqualTo(URLDecoder.decode(expected, "UTF-8"));
    }
}
