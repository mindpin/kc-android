package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/20.
 */
public interface ITopic extends Serializable {
    public String get_id();
    public String get_title();
    public String get_desc();
    public String get_tutorial_count();
    public String get_icon_url();
    public List<ITutorial> get_tutorial_list();
}
