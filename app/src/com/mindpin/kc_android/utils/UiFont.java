package com.mindpin.kc_android.utils;

import android.graphics.Typeface;

import com.mindpin.kc_android.application.KCApplication;

/**
 * Created by fushang318 on 2014/8/20.
 */
public class UiFont {
    final static public Typeface FONTAWESOME_FONT = Typeface.createFromAsset(
            KCApplication.get_context().getAssets(),
            "fonts/fontawesome-webfont.ttf");
}
