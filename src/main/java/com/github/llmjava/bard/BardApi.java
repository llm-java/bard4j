package com.github.llmjava.bard;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface BardApi {

    @GET("/")
    Call<String> getSNlM0e();

    @FormUrlEncoded
    @POST("/_/BardChatUi/data/assistant.lamda.BardFrontendService/StreamGenerate")
    Call<String> generate(
            @QueryMap Map<String, String> params,
            @FieldMap Map<String, String> data
    );
}
