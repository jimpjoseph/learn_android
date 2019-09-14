package org.learning.beatbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class BeatBoxActivity extends SingleFragmentActivity {

    private final static String TAG =  "BeatBoxActivity";
    private SeekBar mVoiceControl;
    private BeatBoxFragment mBeatBoxFragment;


    @Override
    public Fragment createFragment() {
        mBeatBoxFragment = BeatBoxFragment.newInstance();
        return mBeatBoxFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVoiceControl = findViewById(R.id.volume_control);
        mVoiceControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int val, boolean fromuser) {
                float rate = 1.0f * val / 200.0f;
                mBeatBoxFragment.updateReplayRate(rate);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // DO nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });
    }

}
