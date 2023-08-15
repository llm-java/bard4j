package com.github.llmjava.bard;

import com.github.llmjava.bard.internal.HeadersInterceptor;
import com.github.llmjava.bard.internal.SessionCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.time.Duration;

public class BardApiFactory {

    public BardApi build(BardConfig config) {
        String apiKey = config.getApiKey();
        Duration timeout = config.getTimeout();
        BardApi api = buildApi(apiKey, timeout);
        return api;
    }

    BardApi buildApi(String apiKey, Duration timeout) {
        SessionCookieJar cookieJar = new SessionCookieJar.Builder()
                .add(BardConfig.API_KEY, apiKey)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(new HeadersInterceptor())
                .cookieJar(cookieJar)
                .callTimeout(timeout)
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .build();

//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BardConfig.BASE_URL)
                .client(okHttpClient)
//                .addConverterFactory(new Converter.Factory() {
//                })
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(BardApi.class);
    }
}
