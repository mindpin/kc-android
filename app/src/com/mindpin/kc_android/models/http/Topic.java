package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.network.HttpApi;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/21.
 */
public class Topic implements ITopic{
    private String id;
    private String title;
    private String desc;
    private String[] tutorial_ids;
    private List<ITutorial> tutorial_list;

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

    @Override
    public List<ITutorial> get_tutorial_list() {
        if(this.tutorial_list != null){
            return this.tutorial_list;
        }
        try {
            this.tutorial_list = DataProvider.get_topic_tutorial_list(this.get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取主题的教程列表失败");
        }
        return this.tutorial_list;
    }
}
