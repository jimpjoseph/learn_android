package org.learning.photogallery;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {

    public static final String TAG = FlickrFetchr.class.getSimpleName();

    private static final String API_KEY = "a6d819499131071f158fd740860a5a88";


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url =  new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + " : with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer,0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems() {

        List<GalleryItem> items = new ArrayList<>();
        try {

            String url = Uri.parse("https:////api.flickr.com/services/rest/").buildUpon().
                    appendQueryParameter("method","flickr.photos.getRecent").
                    appendQueryParameter("api_key",API_KEY).
                    appendQueryParameter("format","json").
                    appendQueryParameter("nojsoncallback","1").
                    appendQueryParameter("extras", "url_s").
                    build().
                    toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON :\n" + jsonString);
            parseItems(items,jsonString);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return items;
    }

    private void parseItems(List<GalleryItem> items, String jsonBody)  {
        Gson gson = new Gson();
        FlickrPhotoSet photoSet = gson.fromJson(jsonBody, FlickrPhotoSet.class);
        List<FlickrPhoto> photoList = photoSet.getPhotos().getPhoto();
        for (FlickrPhoto photo : photoList) {
            GalleryItem item = new GalleryItem();
            item.setId(photo.getId());
            item.setCaption(photo.getTitle());
            photo.getUrl_s();
            if (photo.getUrl_s() == null || photo.getUrl_s().length() == 0) {
                continue;
            }
            item.setUrl(photo.getUrl_s());
            items.add(item);
        }
    }

}
