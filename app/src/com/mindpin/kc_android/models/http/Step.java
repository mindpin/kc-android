package com.mindpin.kc_android.models.http;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.mindpin.kc_android.models.interfaces.IStep;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fushang318 on 2014/8/12.
 */
public class Step implements IStep{
    private String id;
    private String tutorial_id;
    private String title;
    private String desc;
    @SerializedName("continue")
    private Object _continue;

    @Override
    public String get_id() {
        return this.id;
    }

    @Override
    public String get_title() {
        return this.title;
    }

    @Override
    public String get_desc() {
        return this.desc;
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
            return ((LinkedTreeMap<String,String>)_continue).get("id");
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
        if("select".equals(_get_type())){
            LinkedTreeMap<String,Object> select_map = (LinkedTreeMap<String,Object>)_continue;
            String question = (String)(select_map.get("question"));

            ArrayList<ISelectOption> options = new ArrayList<ISelectOption>();
            ArrayList<LinkedTreeMap<String,String>> option_maps = (ArrayList<LinkedTreeMap<String,String>>) select_map.get("options");
            for(LinkedTreeMap<String,String> o : option_maps){
                SelectOption select = new SelectOption(o.get("id"), o.get("text"));
                options.add(select);
            }
            return new Select(question, options);
        }
        return null;
    }

    private String _get_type(){
        return (String)((LinkedTreeMap<String,Object>)_continue).get("type");
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

}
