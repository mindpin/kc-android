package com.mindpin.kc_android.models.ui_mock;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/7.
 */
public class UIMockKnowledgePoint implements IKnowledgePoint{
    private String name;
    private String desc;
    private String id;

    public UIMockKnowledgePoint(String id) {
        this.id = id;
        this.name = id + id;
        this.desc = id + id + id;
    }
    public UIMockKnowledgePoint() {
        this.id = "point id test";
        this.name = "point name test";
        this.desc = "point desc test";
    }

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
        UIMockKnowledgeNet net = new UIMockKnowledgeNet("hinet");
        return net;
    }

    @Override
    public List<ITutorial> get_tutorial_list() {
        List<ITutorial> list = new ArrayList<ITutorial>();
        for(int i =0; i<20; i++){
            list.add(new UIMockTutorial(String.valueOf(i)));
        }
        return list;
    }
}
