package org.learning.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

public class PhotoGallaryFragment extends Fragment {
    public static final String TAG = PhotoGallaryFragment.class.getSimpleName();

    private RecyclerView mPhotoRecylerView;

    public static PhotoGallaryFragment newInstance() {
        return new PhotoGallaryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mPhotoRecylerView = v.findViewById(R.id.photo_recycler_view);
        mPhotoRecylerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        return v;
    }


    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void ... params) {
            new FlickrFetchr().fetchItems();
            return null;
        }
    }

}
