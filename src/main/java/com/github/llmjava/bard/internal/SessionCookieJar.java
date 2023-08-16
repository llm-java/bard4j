package com.github.llmjava.bard.internal;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

public class SessionCookieJar implements CookieJar {
    private final List<Cookie> cookies;

    SessionCookieJar(Builder builder) {
        this.cookies = builder.cookies;
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        System.out.println("saveFromResponse: " + httpUrl);
        for(Cookie cookie: list) {
            System.out.println(cookie);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        System.out.println("loadForRequest: " + httpUrl);
        return cookies;
    }

    public static class Builder {
        private List<Cookie> cookies = new ArrayList<>();

        public Builder add(Cookie cookie) {
            cookies.add(cookie);
            return this;
        }

        public Builder add(String name, String value) {
            return add(new Cookie.Builder().name(name).value(value).domain("google.com").build());
        }

        public SessionCookieJar build() {
            return new SessionCookieJar(this);
        }
    }
}
