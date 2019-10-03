package org.learning.photogallery;

import androidx.fragment.app.Fragment;

public class PhotGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}
