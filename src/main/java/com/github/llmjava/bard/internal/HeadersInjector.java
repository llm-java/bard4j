package com.github.llmjava.bard.internal;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HeadersInjector implements Interceptor {

    private static final Map<String, String> SESSION_HEADERS = new HashMap<String, String>(){{
        put("Host", "bard.google.com");
        put("X-Same-Domain", "1");
        put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        put("Origin", "https://bard.google.com");
        put("Referer", "https://bard.google.com/");
    }};

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for(Map.Entry<String, String> header: SESSION_HEADERS.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }
        Request request = builder.build();

        return chain.proceed(request);
    }
}
