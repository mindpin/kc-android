package com.mindpin.kc_android.adapter;

        import android.content.Context;
        import android.util.AttributeSet;
        import android.util.Log;
        import android.widget.RelativeLayout;


public class MyLayout extends RelativeLayout {

    public MyLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context) {
        super(context);
    }

    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        int child_width = getMeasuredWidth();
        int child_height = getMeasuredHeight();

        Log.i("child_width 大小 ", Integer.toString(child_width));
        Log.i("child_height 大小 ", Integer.toString(child_height));

        heightMeasureSpec = widthMeasureSpec =
                MeasureSpec.makeMeasureSpec(child_height, MeasureSpec.EXACTLY);

        Log.i("widthMeasureSpec 大小 ", Integer.toString(widthMeasureSpec));
        Log.i("heightMeasureSpec 大小 ", Integer.toString(heightMeasureSpec));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

