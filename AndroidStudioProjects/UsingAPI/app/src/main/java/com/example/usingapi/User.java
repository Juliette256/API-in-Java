package com.example.usingapi;

import android.net.Uri;

public class User {
    int id;
    String title;
    String body;
    String uri;

    public String getUri() { return uri; }

    public void setUri(String uri) { this.uri = uri; }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
