package com.mindpin.kc_android.models.http;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/11.
 */
public class KnowledgeNet implements IKnowledgeNet {
    private String id;
    private String name;
    private String desc;
    private String[] point_ids;


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
        return null;
    }

    @Override
    public List<IKnowledgePoint> get_knowledge_point_list() {
        return null;
    }
}
