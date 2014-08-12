package com.mindpin.kc_android.models.http;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/12.
 */
public class KnowledgePoint implements IKnowledgePoint{
    private String id;
    private String net_id;
    private String name;
    private String desc;

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
        return null;
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        return null;
    }
}
