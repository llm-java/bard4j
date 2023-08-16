package com.github.llmjava.bard.internal;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ResponseParserTest {

    @Test
    public void should_return_SNlM0e() {
        String SNlM0e = ResponseParser.getSNlM0e("\"SNlM0e\":\"yAY4WVGSzXDAAsxckEp-bogileeiidjcenihjaaohdaeidkhbhkp:8496011536757765681\"");
        assertThat(SNlM0e).isEqualTo("yAY4WVGSzXDAAsxckEp-bogileeiidjcenihjaaohdaeidkhbhkp:8496011536757765681");
    }

    @Test
    public void should_return_null_when_SNlM0e_not_found() {
        String SNlM0e = ResponseParser.getSNlM0e("\"xyz\":\"yAY4WVGSzXDAAsxckEp-bogileeiidjcenihjaaohdaeidkhbhkp:8496011536757765681\"");
        assertThat(SNlM0e).isNull();
    }
}
