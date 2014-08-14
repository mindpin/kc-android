package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.HttpApi;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/11.
 */
public class KnowledgeNet implements IKnowledgeNet {
    private String id;
    private String name;
    private String desc;
    private String[] point_ids;
    private List<ITutorial> tutorial_list;
    private List<IKnowledgePoint> knowledge_point_list;


    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public String get_desc() {
        return this.desc;
    }

    @Override
    public int get_point_count() {
        return this.point_ids.length;
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        if(this.tutorial_list != null){
            return this.tutorial_list;
        }
        try {
            this.tutorial_list = HttpApi.get_net_tutorial_list(get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取知识网络的教程列表失败");
        }
        return this.tutorial_list;
    }

    @Override
    public List<IKnowledgePoint> get_knowledge_point_list() {
        if(this.knowledge_point_list != null){
            return this.knowledge_point_list;
        }
        try {
            this.knowledge_point_list = HttpApi.get_net_knowledge_point_list(get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取知识网络的知识点列表失败");
        }
        return this.knowledge_point_list;
    }
}
