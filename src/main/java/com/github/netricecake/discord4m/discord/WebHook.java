package com.github.netricecake.discord4m.discord;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Jsoup;

import java.io.IOException;

public class WebHook {

    private String url;

    private Gson gson;

    private final String urlPrefix = "https://crafatar.com/avatars/"; //Thanks, Crafatar

    public WebHook(String url) {
        this.url = url;
        this.gson = new Gson();
    }

    public void sendWebHook(String name, String msg, String uuid) {
        try {
            JsonObject object = new JsonObject();
            object.addProperty("username", name);
            object.addProperty("avatar_url", urlPrefix + uuid);
            object.addProperty("content", msg);
            object.addProperty("allowed_mentions", false);

            Jsoup.connect(url)
                    .header("Content-Type", "application/json")
                    .requestBody(gson.toJson(object))
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
