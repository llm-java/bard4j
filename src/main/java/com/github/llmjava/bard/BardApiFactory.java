package com.github.llmjava.bard;

import com.github.llmjava.bard.internal.HeadersInjector;
import com.github.llmjava.bard.internal.SessionCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.time.Duration;

public class BardApiFactory {

    private SessionCookieJar cookieJar;

    public void setCookieJar(BardConfig config) {
        cookieJar = new SessionCookieJar.Builder()
                .add("__Secure-1PSID", config.getApiKey())
                .build();
    }
    public BardApi build(BardConfig config) {
        setCookieJar(config);
        Duration timeout = config.getTimeout();
        BardApi api = buildApi(timeout);
        return api;
    }

    private HttpLoggingInterceptor createLogger() {
        Logger logger = s -> System.out.println(s);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(logger);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    BardApi buildApi(Duration timeout) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(new HeadersInjector())
                .addInterceptor(createLogger())
                .callTimeout(timeout)
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BardConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(BardApi.class);
    }
}
