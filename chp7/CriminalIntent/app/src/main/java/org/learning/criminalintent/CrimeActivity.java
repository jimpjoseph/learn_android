package org.learning.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CrimeFragment();
    }

}
