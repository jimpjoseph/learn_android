package org.learning.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME = "org.learning.criminalintent.time";

    private static final String ARG_TIME = "time";

    private TimePicker mTimePicker;
    private static int mYear;
    private static int mMonth;
    private static int mDay;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity()).
                inflate(R.layout.dialog_time, null);

        mTimePicker = v.findViewById(R.id.dialog_time_picker);
        if (Build.VERSION.SDK_INT >= 23) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(min);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(min);
        }

        return new AlertDialog.Builder(getActivity()).
                setView(v).
                setTitle(R.string.time_picker_title).
                setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        int hour, min;
                        if (Build.VERSION.SDK_INT >= 23) {
                            hour = mTimePicker.getHour();
                            min = mTimePicker.getMinute();
                        } else {
                            hour = mTimePicker.getCurrentHour();
                            min = mTimePicker.getCurrentMinute();
                        }

                        Date date = new GregorianCalendar(mYear, mMonth, mDay, hour, min).getTime();
                        sendResult(Activity.RESULT_OK, date);

                    }
                }).
                create();
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

}
