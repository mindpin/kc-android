package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.HttpApi;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/12.
 */
public class Tutorial implements ITutorial{
    private String id;
    private String title;
    private String desc;
    private boolean is_learned;
    private List<IStep> step_list;
    private List<IKnowledgePoint> related_knowledge_point_list;
    private IStep first_step;
    private List<ITutorial> parents;
    private List<ITutorial> children;
    @SerializedName("image")
    private String icon_url;

    private int step_count;
    private int learned_step_count;

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
        return HttpApi.SITE + icon_url;
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
        if(this.related_knowledge_point_list != null){
            return this.related_knowledge_point_list;
        }
        try {
            this.related_knowledge_point_list = HttpApi.get_tutorial_knowledge_point_list(get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取教程的相关知识点失败");
        }
        return this.related_knowledge_point_list;
    }

    @Override
    public List<ITutorial> get_children() {
        if(this.children != null){
            return this.children;
        }
        try {
            this.children = HttpApi.get_child_tutorial_list(this.get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取教程的后续教程列表失败");
        }
        return this.children;
    }

    @Override
    public List<ITutorial> get_parents() {
        if(this.parents != null){
            return this.parents;
        }
        try {
            this.parents = HttpApi.get_parent_tutorial_list(this.get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取教程的前置教程列表失败");
        }
        return this.parents;
    }

    @Override
    @Deprecated
    public List<IStep> get_step_list() {
        if(this.step_list != null){
            return this.step_list;
        }
        try {
            this.step_list = HttpApi.get_step_list(get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取教程的步骤列表失败");
        }
        return this.step_list;
    }

    @Override
    public IStep get_first_step() {
        if(this.first_step != null){
            return this.first_step;
        }
        try{
            get_step_list();
            this.first_step = this.step_list.get(0);
        } catch (Exception e){
            e.printStackTrace();
            Log.d("debug", "获取教程的第一个步骤失败");
        }
        return this.first_step;
    }

    @Override
    public int get_step_count() {
        return this.step_count;
    }

    @Override
    public int get_learned_step_count() {
        return this.learned_step_count;
    }
}
