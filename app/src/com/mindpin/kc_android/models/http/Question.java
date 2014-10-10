package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.mindpin.kc_android.models.interfaces.IQuestion;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.network.HttpApi;

/**
 * Created by fushang318 on 2014/9/25.
 */
public class Question implements IQuestion{
    private String id;
    private Step step;
    private String content;
    private String tutorial_id;
    private String tutorial_image;
    private String created_at;
    private String updated_at;

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

    @Override
    public String get_tutorial_icon_uri() {
        return HttpApi.SITE + this.tutorial_image;
    }

    @Override
    public String get_created_at() {
        return this.created_at;
    }

    @Override
    public String get_updated_at() {
        return this.updated_at;
    }
}
