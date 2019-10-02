package org.learning.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoGallaryFragment extends Fragment {
    public static final String TAG = PhotoGallaryFragment.class.getSimpleName();
    private static final int MAX_PAGE = 10; // Hard limit based on the API.
    private static final int THRESHOLD = 5;
    private RecyclerView mPhotoRecylerView;

    private List<GalleryItem> mItems = new ArrayList<>();
    private int mPageNumber = 1;
    boolean mLoading = false;
    private int mPreviousWidth = 0;

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


        ViewTreeObserver vto = mPhotoRecylerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                if (mPhotoRecylerView.getWidth() != mPreviousWidth) {
                    mPreviousWidth = mPhotoRecylerView.getWidth();
                    Log.d(TAG, "Computed column count : " + mPhotoRecylerView.getWidth() / 360);
                    mPhotoRecylerView.setLayoutManager(new GridLayoutManager(getActivity(),mPhotoRecylerView.getWidth() / 360));
                }
            }
        });

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

            mPhotoRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if ( mPhotoRecylerView.getLayoutManager() instanceof GridLayoutManager) {
                        GridLayoutManager layoutManager = (GridLayoutManager)mPhotoRecylerView.getLayoutManager();
                        int itemCount = layoutManager.getItemCount();
                        int lastVisiblePos = layoutManager.findLastVisibleItemPosition();
                        if (!mLoading && itemCount < lastVisiblePos + THRESHOLD) {
                            mPageNumber++;
                            if (mPageNumber <= MAX_PAGE) {
                                new FetchItemsTask().execute();
                            }
                            mLoading = true;
                        }
                        Log.e(TAG, "Scrolled : item count " +  itemCount + " last pos " + lastVisiblePos);
                    }
                }
            });
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

        public void updateItemList(List<GalleryItem> items) {
            mGalleryItems = items;
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<GalleryItem>> {


        @Override
        protected List<GalleryItem> doInBackground(Void ... params) {
            return new FlickrFetchr().fetchItems(mPageNumber);
        }

        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItems.addAll(items);
            if (mPhotoRecylerView.getAdapter() == null) {
                setupAdapter();
            } else {
                mPhotoRecylerView.getAdapter().notifyDataSetChanged();
            }
            mLoading = false;
        }

    }

}
