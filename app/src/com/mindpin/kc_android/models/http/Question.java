package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.mindpin.kc_android.models.interfaces.IQuestion;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;

/**
 * Created by fushang318 on 2014/9/25.
 */
public class Question implements IQuestion{
    private String id;
    private String step_id;
    private String content;
    private String tutorial_id;

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

    @Override
    public ITutorial get_tutorial() {
        try {
            return DataProvider.get_tutorial(this.tutorial_id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取 question.get_tutorial 失败");
        }
        return null;
    }
}
