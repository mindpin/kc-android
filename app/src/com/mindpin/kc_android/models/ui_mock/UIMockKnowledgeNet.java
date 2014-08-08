package com.mindpin.kc_android.models.ui_mock;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/6.
 */

public class UIMockKnowledgeNet implements IKnowledgeNet , Serializable {
    private static final long serialVersionUID = 2102123532191457L;
    private String name;
    private String desc;
    private int point_count;

    private int count = 0;
    private String id;

    public UIMockKnowledgeNet(int count) {
        this.count = count;
    }

    public UIMockKnowledgeNet(String id) {
        this.id = id;
        this.name = "Net标题" + id;
        this.desc = "Net描述" + id;
        this.count = 5;
        this.point_count = 5;
    }

    @Override
    public String get_id() {
        return this.id;
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
        List<ITutorial> list = new ArrayList<ITutorial>();
        for(int i =0; i<20; i++){
            list.add(new UIMockTutorial(String.valueOf(i)));
        }
        return list;
    }

    @Override
    public List<IKnowledgePoint> get_knowledge_point_list() {
        List<IKnowledgePoint> list = new ArrayList<IKnowledgePoint>();
        for(int i =0; i<5; i++){
            list.add(new UIMockKnowledgePoint(String.valueOf(i)));
        }
        return list;
    }


}
