package com.example.achowdhury.architecture.data;

//not thread safe, add synchronized for safety
public final class SpotifyUtil {
    private static SpotifyUtil instance;
    private String accessToken;

    private SpotifyUtil() {}

    public static SpotifyUtil getInstance() {
        if(instance == null) {
            return new SpotifyUtil();
        }
        else
            return instance;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
