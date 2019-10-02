package org.learning.photogallery;

/**
 * Created by jajoseph on 12/4/16.
 */

public class FlickrPhoto {
    private String id;
    private String owner;
    private String secret;
    private int farm;
    private String title;
    private boolean friend;
    private boolean family;
    private String url_s;
    private String height_s;
    private String width_s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public boolean isFamily() {
        return family;
    }

    public void setFamily(boolean family) {
        this.family = family;
    }

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }

    public String getHeight_s() {
        return height_s;
    }

    public void setHeight_s(String height_s) {
        this.height_s = height_s;
    }

    public String getWidth_s() {
        return width_s;
    }

    public void setWidth_s(String width_s) {
        this.width_s = width_s;
    }

    @Override
    public String toString() {
        return "org.learning.photogallery{\n" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", secret='" + secret + '\'' +
                ", farm=" + farm +
                ", title='" + title + '\'' +
                ", friend=" + friend +
                ", family=" + family +
                ", url_s='" + url_s + '\'' +
                ", height_s='" + height_s + '\'' +
                ", width_s='" + width_s + '\'' +
                "}\n";
    }
}

