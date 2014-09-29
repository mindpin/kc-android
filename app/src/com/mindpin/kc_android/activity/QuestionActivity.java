package com.mindpin.kc_android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseWriteActivity;
import com.mindpin.kc_android.models.interfaces.IQuestion;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

/**
 * Created by dd on 14-9-25.
 */
public class QuestionActivity extends KnowledgeBaseWriteActivity<IQuestion> implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void get_data() {
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                clazz = step.get_question();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                loading_view.hide();
            }
        }.execute();
    }

    @Override
    protected String get_default_write_text() {
        try {
            return clazz == null ?  "" : clazz.get_content();
        } catch (Exception ex) {
            return "";
        }
    }

    @Override
    protected int get_edit_text_write_hint_res_id() {
        return R.string.question_edit_text_hint;
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
        return R.string.delete_question;
    }

    @Override
    protected int get_title_icon_res_id() {
        return R.string.ic_question;
    }

    @Override
    protected int get_title_res_id() {
        if (step.has_question())
            return R.string.edit_question;
        else
            return R.string.create_question;
    }

    @Override
    protected boolean is_actionbar_button_show() {
        return step.has_question();
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
                if ("".equals(et_write.getText().toString())) {
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
                save(v);
                break;
            case R.id.btn_delete:
                delete(v);
                break;
        }
    }

    private void delete(final View v) {
        new KCAsyncTask<Void>(this) {
            @Override
            protected void onPreExecute() throws Exception {
                v.setEnabled(false);
            }

            @Override
            public Void call() throws Exception {
                step.destroy_question();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                finish_with_result();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                v.setEnabled(true);
            }
        }.execute();
    }

    private void save(final View v) {
        new KCAsyncTask<Void>(this) {
            @Override
            protected void onPreExecute() throws Exception {
                v.setEnabled(false);
            }

            @Override
            public Void call() throws Exception {
                if (step.has_question()) {
                    step.edit_question(et_write.getText().toString());
                } else {
                    step.create_question(et_write.getText().toString());
                }
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                finish_with_result();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                v.setEnabled(true);
            }
        }.execute();
    }
}