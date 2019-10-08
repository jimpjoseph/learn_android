package org.learning.locatr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class LocatrActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LocatorFragment.newInstance();
    }
}
