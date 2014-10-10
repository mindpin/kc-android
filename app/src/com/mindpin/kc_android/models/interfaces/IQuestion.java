package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;

/**
 * Created by fushang318 on 2014/9/25.
 */
public interface IQuestion extends Serializable {
    public String get_id();
    public IStep get_step();
    public String get_step_id();
    public String get_content();
    public String get_tutorial_id();
    public String get_tutorial_icon_uri();
    public String get_created_at();
    public String get_updated_at();
}
