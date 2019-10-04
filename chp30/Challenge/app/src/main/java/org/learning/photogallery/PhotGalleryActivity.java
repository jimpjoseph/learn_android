package org.learning.photogallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PhotGalleryActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, PhotGalleryActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return PhotoGallaryFragment.newInstance();
    }
}
