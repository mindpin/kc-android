package com.mindpin.kc_android.models.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.network.HttpApi;

import java.io.Serializable;
import java.util.*;


/**
 * Created by fushang318 on 2014/8/12.
 */
public class Step implements IStep{
    private String id;
    private String tutorial_id;
    private String title;
    @SerializedName("continue")
    private Continue _continue;
    private List<ContentBlock> blocks;
    private boolean is_learned;
    private boolean is_hard;
    private String question_id;
    private String note_id;

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public String get_title() {
        return this.title;
    }

    @Override
    public ContinueType get_continue_type() {
        if("select".equals(_get_type())){
            return ContinueType.SELECT;
        }

        if("step".equals(_get_type())){
            return ContinueType.STEP;
        }

        if("end".equals(_get_type())){
            return ContinueType.END;
        }
        return null;
    }

    @Override
    public String get_next_id() {
        if("step".equals(_get_type())){
            return _continue.get_id();
        }
        return null;
    }

    @Override
    public boolean is_end() {
        if("end".equals(_get_type())){
            return true;
        }
        return false;
    }

    @Override
    public ISelect get_select() {
        if(get_continue_type() == ContinueType.SELECT){
            String question = _continue.getQuestion();

            ArrayList<ISelectOption> options = new ArrayList<ISelectOption>();
            for(SelectOption o : _continue.getOptions()){
                SelectOption select = new SelectOption(o.get_next_step_id(), o.get_text());
                options.add(select);
            }
            return new Select(question, options);
        }
        return null;
    }

    @Override
    public List<IContentBlock> get_blocks() {
        return new ArrayList<IContentBlock>(this.blocks);
    }

    @Override
    public boolean is_learned() {
        return this.is_learned;
    }

    @Override
    public void do_learn() {
        if(!this.is_learned()){
            try {
                HttpApi.learn_step(this.get_id());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("debug", "标记学习步骤失败");
            }
        }
    }

    @Override
    public boolean has_question() {
        return this.question_id != null && this.question_id != "";
    }

    @Override
    public boolean has_note() {
        return this.note_id != null && this.note_id != "";
    }

    @Override
    public boolean is_hard() {
        return this.is_hard;
    }

    @Override
    public String get_question_id() {
        return this.question_id;
    }

    @Override
    public String get_note_id() {
        return this.note_id;
    }

    private String _get_type(){
        return _continue.getType();
    }

    class Continue implements Serializable{
        private String id;
        private String type;
        private String question;
        private List<SelectOption> options;

        public String get_id() {
            return id;
        }

        public String getType() {
            return type;
        }

        public String getQuestion() {
            return question;
        }

        public List<SelectOption> getOptions() {
            return options;
        }

    }

    class Select implements ISelect{
        private String question;
        private List<ISelectOption> select_options;

        public Select(String question, List<ISelectOption> select_options){
            this.question = question;
            this.select_options = select_options;
        }

        @Override
        public String get_question() {
            return this.question;
        }

        @Override
        public List<ISelectOption> get_select_options() {
            return this.select_options;
        }
    }

    class SelectOption implements ISelectOption{
        private String text;
        private String id;

        public SelectOption(String id, String text){
            this.id = id;
            this.text = text;
        }

        @Override
        public String get_next_step_id() {
            return this.id;
        }

        @Override
        public String get_text() {
            return this.text;
        }
    }

    class ContentBlock implements IContentBlock{
        private String kind;
        private String content;
        private VirtualFile virtual_file;

        @Override
        public ContentKind get_kind() {
            if("text".equals(kind)){
                return ContentKind.TEXT;
            }
            if("image".equals(kind)){
                return ContentKind.IMAGE;
            }
            if("video".equals(kind)){
                return ContentKind.VIDEO;
            }

            return null;
        }

        @Override
        public String get_url() {
            if(get_kind() == ContentKind.IMAGE || get_kind() == ContentKind.VIDEO){
                String url = virtual_file.get_url();
                return HttpApi.SITE + url;
            }
            return null;
        }

        @Override
        public String get_content() {
            if(get_kind() == ContentKind.TEXT){
                return this.content;
            }
            return null;
        }

        @Override
        public Integer get_duration() {
            return virtual_file != null ? virtual_file.get_duration() : null;
        }

        @Override
        public Integer get_width() {
            return virtual_file != null ? virtual_file.get_width() : null;
        }

        @Override
        public Integer get_height() {
            return virtual_file != null ? virtual_file.get_height() : null;
        }

        @Override
        public VirtualFile get_virtual_file() {
            return virtual_file;
        }

        class VirtualFile implements IVirtualFile{
            private String id;
            private String name;
            private String virtual_path;
            private String url;
            private boolean is_dir;
            private Integer duration;
            private Integer width;
            private Integer height;

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getVirtual_path() {
                return virtual_path;
            }

            public String get_url() {
                return url;
            }

            public boolean isIs_dir() {
                return is_dir;
            }

            public Integer get_duration(){
                return duration;
            }

            public Integer get_width(){
                return width;
            }

            public Integer get_height(){
                return height;
            }

            @Override
            public String toString() {
                return new Gson().toJson(this, VirtualFile.class);
            }
        }
    }

}
