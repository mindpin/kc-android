package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public interface IKnowledgePoint extends Serializable {
    public String get_id();
    public String get_name();
    public String get_desc();
    public IKnowledgeNet get_knowledge_net();

    public List<ITutorial> get_tutorial_list();
}
