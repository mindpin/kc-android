package com.mindpin.kc_android.models.ui_mock;

import com.mindpin.kc_android.models.interfaces.IStep;

/**
 * Created by fushang318 on 2014/8/7.
 */
public class UIMockStep implements IStep{
    private String title;
    private String desc;

    @Override
    public String get_title() {
        return this.title;
    }

    @Override
    public String get_desc() {
        return this.desc;
    }
}
