package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public interface IStep extends Serializable {
    public String get_id();
    public String get_title();
    public ContinueType get_continue_type();
    public String get_next_id();
    public boolean is_end();
    public ISelect get_select();
    public List<IContentBlock> get_blocks();

    enum ContinueType{
        STEP,SELECT,END
    }

    interface ISelect extends Serializable{
        public String get_question();
        public List<ISelectOption> get_select_options();
    }

    interface ISelectOption extends Serializable{
        public String get_next_step_id();
        public String get_text();
    }

    interface IContentBlock extends Serializable {
        enum ContentKind{
            TEXT, IMAGE, VIDEO
        }
        public ContentKind get_kind();
        public String get_url();
        public String get_content();
    }
}
