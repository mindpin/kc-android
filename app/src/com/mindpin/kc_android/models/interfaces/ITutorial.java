package com.mindpin.kc_android.models.interfaces;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public interface ITutorial {
    public String get_title();
    public String get_desc();
    public String get_icon_url();
    public String get_knowledge_net_id();
    public boolean current_user_is_Learned();

    public List<IKnowledgePoint> get_related_knowledge_point_list();
    public List<ITutorial> get_children();
    public List<IStep> get_step_list();
}
