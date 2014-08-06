package com.mindpin.kc_android.models.ui_mock;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/6.
 */
public class UIMockKnowledgeNet implements IKnowledgeNet{
    private String name;
    private String desc;
    private int point_count;

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
        return this.point_count;
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        // TODO 硬编码创建假数据
        return null;
    }

    @Override
    public List<IKnowledgePoint> get_knowledge_point_list() {
        // TODO 硬编码创建假数据
        return null;
    }
}
