package org.learning.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoGallaryFragment extends Fragment {
    public static final String TAG = PhotoGallaryFragment.class.getSimpleName();

    private RecyclerView mPhotoRecylerView;

    private List<GalleryItem> mItems = new ArrayList<>();

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

        setupAdapter();
        return v;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mPhotoRecylerView.setAdapter(new PhotoAdaper(mItems));
        }
    }


    private class PhotoHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;

        public PhotoHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView;
        }

        public void bindGaleryItem(GalleryItem item) {
            mTitleTextView.setText(item.toString());
        }
    }


    private class PhotoAdaper extends RecyclerView.Adapter<PhotoHolder> {

        private List<GalleryItem> mGalleryItems;

        public PhotoAdaper(List<GalleryItem> items) {
            mGalleryItems = items;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            TextView textView = new TextView(getActivity());
            return new PhotoHolder(textView);
        }

        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position) {
            GalleryItem item = mGalleryItems.get(position);
            photoHolder.bindGaleryItem(item);
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<GalleryItem>> {

        @Override
        protected List<GalleryItem> doInBackground(Void ... params) {
            return new FlickrFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItems = items;
            setupAdapter();
        }

    }

}
