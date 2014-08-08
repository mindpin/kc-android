package com.mindpin.kc_android.models.ui_mock;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/7.
 */
public class UIMockKnowledgePoint implements IKnowledgePoint{
    private String name;
    private String desc;
    private String id;

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
        // TODO 硬编码创建假数据
        return null;
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        // TODO 硬编码创建假数据
        return null;
    }
}
