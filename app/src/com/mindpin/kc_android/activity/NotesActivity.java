package com.mindpin.kc_android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseWriteActivity;

/**
 * Created by dd on 14-9-25.
 */
public class NotesActivity extends KnowledgeBaseWriteActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int get_edit_text_write_hint_res_id() {
        return R.string.notes_edit_text_hint;
    }

    @Override
    protected View.OnClickListener get_button_cancel_on_click_listener(View btn_cancel) {
        //todo bind
        return this;
    }

    @Override
    protected View.OnClickListener get_button_save_on_click_listener(View btn_save) {
        //todo bind
        return this;
    }

    @Override
    protected View.OnClickListener get_actionbar_button_on_click_listener(View btn_write_actionbar) {
        //todo bind
        return this;
    }

    @Override
    protected int get_actionbar_button_text_res_id() {
        return R.string.delete_notes;
    }

    @Override
    protected int get_title_icon_res_id() {
        return R.string.fontawesome_note_square;
    }

    @Override
    protected int get_title_res_id() {
        //todo for step
//        if(step.is_noted)
//        return R.string.edit_notes;
//        else
        return R.string.create_notes;
    }

    @Override
    protected boolean is_actionbar_button_show() {
        //todo for step
//        return step.is_noted
        return false;
//        return true; // todo for test
    }

    @Override
    public void onClick(View v) {
        //todo for btn click
        switch (v.getId()){
            case R.id.btn_cancel:
                System.out.println("btn_cancel");
                break;
            case R.id.btn_save:
                System.out.println("btn_save");
                break;
            case R.id.btn_write_actionbar:
                System.out.println("btn_write_actionbar");
                break;
        }
    }
}