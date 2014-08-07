package com.mindpin.kc_android;


import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

public class KnowledgeNet implements IKnowledgeNet {
    private int count = 0;

    public KnowledgeNet(int count) {
        this.count = count;
    }

    public String get_name() {
        return "hello name " + Integer.toString(count);
    }

    public String get_desc() {
        return "hello desc " + Integer.toString(count);
    }

    public int get_point_count() {
        return count;
    }

    public List<ITutorial> get_tutorial_list() {
        return null;
    }

    public List<IKnowledgePoint> get_knowledge_point_list() {
        return null;

    }
}
