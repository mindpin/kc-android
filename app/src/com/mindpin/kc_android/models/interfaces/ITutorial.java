package com.mindpin.kc_android.models.interfaces;

import com.mindpin.kc_android.models.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public interface ITutorial extends Serializable {
    public String get_id();
    public String get_title();
    public String get_desc();
    public String get_icon_url();
    public String get_knowledge_net_id();
    public boolean current_user_is_Learned();

    public List<IKnowledgePoint> get_related_knowledge_point_list();
    public List<ITutorial> get_children();
    public List<ITutorial> get_parents();
    
    @Deprecated
    public List<IStep> get_step_list();

    public IStep get_first_step();

    public int get_step_count();
    public int get_learned_step_count();
    public User get_creator();
    public String get_topic_id();
}
