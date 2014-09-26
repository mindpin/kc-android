package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;

/**
 * Created by fushang318 on 2014/9/25.
 */
public interface IQuestion extends Serializable {
    public String get_id();
    public String get_step_id();
    public String get_content();
    public ITutorial get_tutorial();
}
