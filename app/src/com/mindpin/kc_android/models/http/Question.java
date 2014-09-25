package com.mindpin.kc_android.models.http;

import com.mindpin.kc_android.models.interfaces.IQuestion;

/**
 * Created by fushang318 on 2014/9/25.
 */
public class Question implements IQuestion{
    private String id;
    private String step_id;
    private String content;

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public String get_step_id() {
        return this.step_id;
    }

    @Override
    public String get_content() {
        return this.content;
    }
}
