package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.mindpin.kc_android.models.interfaces.INote;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;

/**
 * Created by fushang318 on 2014/9/25.
 */
public class Note implements INote{
    private String id;
    private Step step;
    private String content;
    private String tutorial_id;

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public IStep get_step() {
        return this.step;
    }

    @Override
    public String get_step_id() {
        return this.step.get_id();
    }

    @Override
    public String get_content() {
        return this.content;
    }

    @Override
    public String get_tutorial_id() {
        return this.tutorial_id;
    }

}
