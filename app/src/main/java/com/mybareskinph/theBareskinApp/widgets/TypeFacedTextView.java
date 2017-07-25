package com.mybareskinph.theBareskinApp.widgets;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mybareskinph.theBareskinApp.R;

public class TypeFacedTextView extends AppCompatTextView {
    String fontString = "Roboto-Regular.ttf";

    public TypeFacedTextView(Context context) {
        super(context);
        init(null);
    }

    public TypeFacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TypeFacedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FontSelector, 0, 0);
            if (a.getString(R.styleable.FontSelector_font) != null) {
                fontString = a.getString(R.styleable.FontSelector_font);
                if ("montserrat_regular".equals(fontString) || "montserrat_thin".equals(fontString)) {
                    fontString += ".otf";
                } else {
                    fontString += ".ttf";
                }
            }

            Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontString);
            setTypeface(font);
            a.recycle();
        }
    }
}
