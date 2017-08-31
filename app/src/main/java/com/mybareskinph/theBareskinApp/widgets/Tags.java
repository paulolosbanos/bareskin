package com.mybareskinph.theBareskinApp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mybareskinph.theBareskinApp.R;

import rx.subjects.PublishSubject;

/**
 * Created by paulolosbanos on 8/7/17.
 */

public class Tags extends LinearLayout {
    PublishSubject<String> subject;

    public Tags(Context context, String text, PublishSubject<String> subject) {
        super(context);
        this.subject = subject;
        init(text);
    }

    public Tags(Context context, AttributeSet attrs) {
        super(context, attrs);
        init("");
    }

    public Tags(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init("");
    }

    private void init(String text) {
        View view = inflate(getContext(), R.layout.view_tags_layout, this);
        TextView t = ((TextView) view.findViewById(R.id.tv_tag));
        t.setText(text);
        RxView.clicks(t).subscribe(aVoid -> subject.onNext(text));
    }
}
