package com.mindpin.kc_android.models.http;

import com.mindpin.kc_android.models.interfaces.ITopic;

/**
 * Created by fushang318 on 2014/8/21.
 */
public class Topic implements ITopic{
    private String id;
    private String title;
    private String desc;
    private String[] tutorial_ids;

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public String get_title() {
        return this.title;
    }

    @Override
    public String get_desc() {
        return this.desc;
    }

    @Override
    public String get_tutorial_count() {
        return this.tutorial_ids.length+"";
    }

    @Override
    public String get_icon_url() {
        return "";
    }
}
