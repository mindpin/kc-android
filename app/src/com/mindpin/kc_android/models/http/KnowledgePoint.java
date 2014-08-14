package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.HttpApi;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/12.
 */
public class KnowledgePoint implements IKnowledgePoint{
    private String id;
    private String net_id;
    private String name;
    private String desc;
    private List<ITutorial> tutorial_list;
    private IKnowledgeNet knowledge_net;

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
    public IKnowledgeNet get_knowledge_net() {
        if(this.knowledge_net != null){
            return this.knowledge_net;
        }
        try {
            this.knowledge_net = HttpApi.get_knowledge_net(net_id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取知识点的所属知识网络失败");
        }
        return this.knowledge_net;
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        if(this.tutorial_list != null){
            return this.tutorial_list;
        }
        try {
            this.tutorial_list = HttpApi.get_point_tutorial_list(get_id());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("debug", "获取知识点的相关教程列表失败");
        }
        return this.tutorial_list;
    }
}
