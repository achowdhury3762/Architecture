package com.example.achowdhury.architecture.data.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotifyApi {
    @GET("authorize")
    Call<ResponseBody> authorizeClient(@Query("client_id") String client_id,
                                       @Query("redirect_uri") String redirect_uri,
                                       @Query("response_type") String response_type);
}
