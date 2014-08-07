package com.mindpin.kc_android.models.ui_mock;

import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/7.
 */
public class UIMockTutorial implements ITutorial{
    private String title;
    private String desc;
    private String icon_url;
    private String knowledge_net_id;
    private boolean is_learned;

    @Override
    public String get_title() {
        return this.title;
    }

    @Override
    public String get_desc() {
        return this.desc;
    }

    @Override
    public String get_icon_url() {
        return this.icon_url;
    }

    @Override
    public String get_knowledge_net_id() {
        return this.knowledge_net_id;
    }

    @Override
    public boolean current_user_is_Learned() {
        return this.is_learned;
    }

    @Override
    public List<IKnowledgePoint> get_related_knowledge_point_list() {
        // TODO 硬编码创建假数据
        return null;
    }

    @Override
    public List<ITutorial> get_children() {
        // TODO 硬编码创建假数据
        return null;
    }

    @Override
    public List<IStep> get_step_list() {
        // TODO 硬编码创建假数据
        return null;
    }
}
