package com.mindpin.kc_android.models.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public interface IStep extends Serializable {
    public String get_id();
    public String get_title();
    public String get_created_at();
    public String get_updated_at();
    public ContinueType get_continue_type();
    public String get_next_id();
    public boolean is_end();
    public ISelect get_select();
    public List<IContentBlock> get_blocks();
    public boolean is_learned();
    public void do_learn();
    public boolean has_question();
    public boolean has_note();
    public boolean is_hard();
    public String get_question_id();
    public String get_note_id();
    public void create_question(String content);
    public IQuestion get_question();
    public void edit_question(String content);
    public void destroy_question();
    public void create_note(String content);
    public INote get_note();
    public void edit_note(String content);
    public void destroy_note();
    public void set_hard();
    public void unset_hard();
    public ITutorial get_tutorial();
    public String get_tutorial_id();

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
        interface IVirtualFile extends Serializable{
            public Integer get_duration();
            public Integer get_width();
            public Integer get_height();
        }
        public ContentKind get_kind();
        public String get_url();
        public String get_content();
        public Integer get_duration();
        public Integer get_width();
        public Integer get_height();
        public IVirtualFile get_virtual_file();
    }
}
