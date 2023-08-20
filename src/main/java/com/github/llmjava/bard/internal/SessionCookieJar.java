package com.github.llmjava.bard.internal;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.*;

public class SessionCookieJar implements CookieJar {
    private final Set<Cookie> cookies;

    SessionCookieJar(Builder builder) {
        this.cookies = builder.cookies;
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        cookies.addAll(list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return new ArrayList<>(cookies);
    }

    public static class Builder {
        private Set<Cookie> cookies = new HashSet<>();

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
