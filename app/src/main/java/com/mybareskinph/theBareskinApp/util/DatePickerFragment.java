package com.mybareskinph.theBareskinApp.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.mybareskinph.theBareskinApp.R;

import java.util.Calendar;

/**
 * Created by paulolosbanos on 7/10/17.
 */

public class DatePickerFragment extends DialogFragment {

    public static final String YEAR = "year";
    public static final String MONTH_OF_YEAR = "monthOfYear";
    public static final String DAY_OF_MONTH = "dayOfMonth";
    public static final String NAME = "name";
    public static final String TAG = "datePicker";

    @Nullable
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Nullable
    private DatePickerFragmentListener listener;

    @Nullable
    Calendar maxDate;

    public static DatePickerFragment newInstance(String name, CalendarDate calendarDate) {
        return newInstance(name, calendarDate.getYear(), calendarDate.getMonthOfYear(), calendarDate.getDayOfMonth());
    }

    public static DatePickerFragment newInstance(String name, int year, int monthOfYear, int dayOfMonth) {
        DatePickerFragment result = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(YEAR, year);
        args.putInt(MONTH_OF_YEAR, monthOfYear);
        args.putInt(DAY_OF_MONTH, dayOfMonth);
        result.setArguments(args);
        return result;
    }

    public static void show(FragmentManager fragmentManager, String name, int year, int monthOfYear,
                            int dayOfMonth) {
        DatePickerFragment.newInstance(name, year, monthOfYear, dayOfMonth).show(fragmentManager,
                TAG);
    }

    public static void show(FragmentManager fragmentManager, String name, CalendarDate calendarDate) {
        show(fragmentManager, name, calendarDate.getYear(), calendarDate.getMonthOfYear(),
                calendarDate.getDayOfMonth());
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, NAME);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DatePickerFragmentListener) {
            this.listener = (DatePickerFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onDateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            if (listener != null) {
                listener.dateSelected(getArguments().getString(NAME), year, monthOfYear, dayOfMonth);
            }
        };
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle arguments = getArguments();
        final int year = arguments.getInt(YEAR);
        final int monthOfYear = arguments.getInt(MONTH_OF_YEAR);
        final int dayOfMonth = arguments.getInt(DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog,
                onDateSetListener, year, monthOfYear, dayOfMonth);
        if (this.maxDate != null) {
            dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
        }
        return dialog;
    }

    public void setMaxDate(@Nullable Calendar calendar) {
        maxDate = calendar;
    }

    public interface DatePickerFragmentListener {
        void dateSelected(String name, int year, int monthOfYear, int dayOfMonth);
    }

    public void setListener(@Nullable DatePickerFragmentListener listener) {
        this.listener = listener;
    }
}
