package com.mybareskinph.theBareskinApp.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.mybareskinph.theBareskinApp.R;

/**
 * Created by paulolosbanos on 8/24/17.
 */

public class VerticalDashedLine extends View {

    private Paint paint;
    private boolean isHorizontal = false;

    public VerticalDashedLine(Context context) {
        super(context);
        init(context);
    }

    public VerticalDashedLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public VerticalDashedLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    private void init(Context context) {
        init(null, context);
    }

    private void init(AttributeSet attrs, Context context) {
        Resources res = getResources();

        int color = ContextCompat.getColor(context, R.color.vertical_dashed_line_color);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.VerticalDashedLine, 0, 0);
            color = a.getColor(R.styleable.VerticalDashedLine_color, color);
            isHorizontal = a.getBoolean(R.styleable.VerticalDashedLine_isHorizontal, false);
        }

        paint = new Paint();

        int width = res.getDimensionPixelSize(R.dimen.vertical_dashed_line_width);
        int length = res.getDimensionPixelSize(R.dimen.vertical_dashed_line_length);
        int gap = res.getDimensionPixelSize(R.dimen.vertical_dashed_line_gap);

        if (isHorizontal) {
            width = res.getDimensionPixelSize(R.dimen.vertical_dashed_line_length);
            length = res.getDimensionPixelSize(R.dimen.vertical_dashed_line_width);
        }

        paint.setStyle(Paint.Style.FILL);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setPathEffect(new DashPathEffect(new float[]{length, gap,}, 0));

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isHorizontal) {
            canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        } else {
            canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
        }
    }
}
