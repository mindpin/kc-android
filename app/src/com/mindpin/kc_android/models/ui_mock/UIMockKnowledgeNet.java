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

    private int count = 0;

    public UIMockKnowledgeNet(int count) {
        this.count = count;
    }

    @Override
    public String get_name() {
        return "hello name " + Integer.toString(count);
    }

    @Override
    public String get_desc() {
        return "hello desc " + Integer.toString(count);
    }

    @Override
    public int get_point_count() {
        return count;
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
