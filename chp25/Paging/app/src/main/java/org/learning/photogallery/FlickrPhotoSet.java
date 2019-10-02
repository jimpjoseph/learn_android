package org.learning.photogallery;

/**
 * Created by jajoseph on 12/4/16.
 */

public class FlickrPhotoSet {
    private FlickrPhotos photos;
    private String stat;

    public FlickrPhotos getPhotos() {
        return photos;
    }

    public void setPhotos(FlickrPhotos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "org.learning.photogallery \n{" +
                "photos=" + photos +
                "}\n";
    }
}
