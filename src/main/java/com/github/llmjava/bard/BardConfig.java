package com.github.llmjava.bard;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class BardConfig {

    public static final String BASE_URL = "https://bard.google.com/";

    /**
     *  Get __Secure-1PSID from your browser cookies
     */
    public static final String API_KEY = "bard.apiKey";

    /**
     * ALLOWED_LANGUAGES: "en", "ko", "ja", "english", "korean", "japanese"
     */
    public static final String LANGUAGE = "bard.language";

    public static final String LANGUAGE_DEFAULT = "en";


    public static final String TIMEOUT = "timeout";

    public static final Long DEFAULT_TIMEOUT_MILLIS = 10*1000l;

    private final Configuration config;

    public BardConfig(Configuration config) {
        this.config = config;
    }

    public String getApiKey() {
        return config.getString(API_KEY);
    }

    public Duration getTimeout() {
        Long timeout = config.getLong(BardConfig.TIMEOUT, BardConfig.DEFAULT_TIMEOUT_MILLIS);
        return Duration.ofMillis(timeout);
    }

    public static BardConfig fromProperties(String path) throws ConfigurationException {
        Configuration baseConfig = new Configurations().properties(path);
        return new BardConfig(baseConfig);
    }
}
