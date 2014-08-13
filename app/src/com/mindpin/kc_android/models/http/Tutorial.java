package com.mindpin.kc_android.models.http;

import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/12.
 */
public class Tutorial implements ITutorial{
    private String id;
    private String title;
    private String desc;
    private boolean is_learned;


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
    public String get_icon_url() {
        return "http://placekitten.com/g/48/48";
    }

    @Override
    public String get_knowledge_net_id() {
        return null;
    }

    @Override
    public boolean current_user_is_Learned() {
        return this.is_learned;
    }

    @Override
    public List<IKnowledgePoint> get_related_knowledge_point_list() {
        return null;
    }

    @Override
    public List<ITutorial> get_children() {
        return null;
    }

    @Override
    public List<IStep> get_step_list() {
        return null;
    }
}
