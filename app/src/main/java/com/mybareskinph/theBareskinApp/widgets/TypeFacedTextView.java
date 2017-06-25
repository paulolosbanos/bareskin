package com.mybareskinph.theBareskinApp.widgets;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TypeFacedTextView extends AppCompatTextView {
    public TypeFacedTextView(Context context) {
        super(context);
        init();
    }

    public TypeFacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypeFacedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf");
        this.setTypeface(font);
    }
}
