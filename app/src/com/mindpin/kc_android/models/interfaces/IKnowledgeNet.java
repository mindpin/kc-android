package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public interface IKnowledgeNet extends Serializable {
    public String get_id();
    public String get_name();
    public String get_desc();
    public int get_point_count();

    public List<ITutorial> get_tutorial_list();
    public List<IKnowledgePoint> get_knowledge_point_list();
}
