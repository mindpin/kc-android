package com.mindpin.kc_android.models;

import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

public class ITopicData implements ITopic{

    public String get_id() {
        return "id-test";
    }


    public String get_title() {
        return "title-test";
    }


    public String get_desc() {
        return "blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla blabla";
    }


    public String get_tutorial_count() {
        return "2";
    }


    public String get_icon_url() {
        return "http://mindpin.oss-cn-hangzhou.aliyuncs.com/image_service/images/pQRqVPaU/pQRqVPaU.png";
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        return null;
    }
}
