package org.learning.locatr;

import android.location.Location;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
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

    private static final String FETCH_RECENT_METHOD = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";

    private static final Uri ENDPOINT =  Uri.parse("https://api.flickr.com/services/rest/")
            .buildUpon().
            appendQueryParameter("api_key",API_KEY).
            appendQueryParameter("format","json").
            appendQueryParameter("nojsoncallback","1").
            appendQueryParameter("extras", "url_s").
            build();

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

    public List<GalleryItem> fetchRecentPhotos() {
        return downloadGalleryItems(buildUrl(FETCH_RECENT_METHOD,null));
    }

    public List<GalleryItem> searchPhotos(String query) {
        return downloadGalleryItems(buildUrl(SEARCH_METHOD, query));
    }

    public List<GalleryItem> searchPhotos(Location location) {
        return downloadGalleryItems(buildUrl(location));
    }

    public List<GalleryItem> downloadGalleryItems(String url) {

        List<GalleryItem> items = new ArrayList<>();
        try {

            String jsonString = getUrlString(url);

            Log.i(TAG, "Received JSON :\n" + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items,jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
            Log.e(TAG, ioe.getMessage());

        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse jsob", je);
        }
        return items;
    }

    private String buildUrl(String method, String query) {
        Uri.Builder uriBuilder = ENDPOINT.
                buildUpon().
                appendQueryParameter("method", method);

        if (method.equals(SEARCH_METHOD)) {
            uriBuilder.appendQueryParameter("text", query);
        }
        return uriBuilder.build().toString();
    }

    private String buildUrl(Location location) {
        return ENDPOINT.buildUpon()
                .appendQueryParameter("method", SEARCH_METHOD)
                .appendQueryParameter("lat", "" + location.getLatitude())
                .appendQueryParameter("lon", "" + location.getLongitude())
                .build()
                .toString();

    }


    private void parseItems(List<GalleryItem> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);
            GalleryItem item = new GalleryItem();

            item.setId(photoJsonObject.getString("id"));
            item.setCaption(photoJsonObject.getString("title"));

            if (!photoJsonObject.has("url_s") || !photoJsonObject.has("owner")) {
                Log.w(TAG,"Item with id: " + item.getId() +  " Title: " + item.getCaption() + " discarded");
                continue;
            }

            item.setUrl(photoJsonObject.getString("url_s"));

            item.setOwner(photoJsonObject.getString("owner"));
            items.add(item);
        }
    }

}
