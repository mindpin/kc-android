package com.mindpin.kc_android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
import com.mindpin.kc_android.utils.UiFont;

public class FontAwesomeTextView extends TextView {

    public FontAwesomeTextView(Context context) {
        super(context);
        init();
    }

    public FontAwesomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontAwesomeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(UiFont.FONTAWESOME_FONT);
        }
        setGravity(Gravity.CENTER);
    }
}