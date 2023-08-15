package com.github.llmjava.bard;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BardApi {

    @GET("/")
    Call<String> getSNlM0e();
}
