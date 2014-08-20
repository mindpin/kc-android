package com.mindpin.kc_android.models;

import com.mindpin.kc_android.models.interfaces.ITopic;

public class ITopicData implements ITopic{

    public String get_id() {
        return "id-test";
    }


    public String get_title() {
        return "title-test";
    }


    public String get_desc() {
        return "desc-test";
    }


    public String get_tutorial_count() {
        return "count-test";
    }


    public String get_icon_url() {
        return "";
    }
}
