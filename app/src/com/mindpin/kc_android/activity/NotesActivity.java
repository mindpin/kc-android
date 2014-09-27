package com.mindpin.kc_android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    protected String get_default_write_text() {
        return step.has_note() ? step.get_note().get_content() : "";
    }

    @Override
    protected int get_edit_text_write_hint_res_id() {
        return R.string.notes_edit_text_hint;
    }

    @Override
    protected View.OnClickListener get_button_cancel_on_click_listener(View btn_cancel) {
        return this;
    }

    @Override
    protected View.OnClickListener get_button_save_on_click_listener(View btn_save) {
        return this;
    }

    @Override
    protected View.OnClickListener get_actionbar_button_on_click_listener(View btn_write_actionbar) {
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
        if (step.has_note())
            return R.string.edit_notes;
        else
            return R.string.create_notes;
    }

    @Override
    protected boolean is_actionbar_button_show() {
        return step.has_note();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                System.out.println("btn_cancel");
                finish();
                break;
            case R.id.btn_save:
                System.out.println("btn_save");
                if("".equals(et_write.getText().toString())){
                    new AlertDialog.Builder(this).setTitle("提示")
                            .setMessage("未输入任何内容")
                            .setNegativeButton("取消提交", null)
                            .setPositiveButton("退出页面", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).create().show();
                    return;
                }
                if (step.has_note()) {
                    step.edit_note(et_write.getText().toString());
                } else {
                    step.create_note(et_write.getText().toString());
                }
                finish_with_result();
                break;
            case R.id.btn_delete:
                if (step.has_note()) {
                    step.destroy_note();
                }
                finish_with_result();
                System.out.println("btn_write_actionbar");
                break;
        }
    }
}