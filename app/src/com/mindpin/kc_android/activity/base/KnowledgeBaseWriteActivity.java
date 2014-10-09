package com.mindpin.kc_android.activity.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.models.http.Step;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.mindpin.kc_android.widget.FontAwesomeTextView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by fushang318 on 2014/8/14.
 */
public abstract class KnowledgeBaseWriteActivity<Clazz> extends KnowledgeBaseActivity implements View.OnClickListener {
    private static final int CODE_STEP_ACTIONS = 0;
    @InjectExtra("step")
    protected Step step;
    @InjectView(R.id.et_write)
    protected EditText et_write;
    @InjectView(R.id.btn_delete)
    protected Button btn_delete;
    @InjectView(R.id.btn_save)
    protected Button btn_save;
    @InjectView(R.id.btn_cancel)
    protected Button btn_cancel;
    @InjectView(R.id.loading_view)
    protected LoadingView loading_view;
    protected Intent mIntent;
    protected Clazz clazz = null;
    @InjectView(R.id.fatv_write_actionbar_icon)
    FontAwesomeTextView fatv_write_actionbar_icon;
    @InjectView(R.id.tv_write_actionbar_title)
    TextView tv_write_actionbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
        bind_views();
        get_data_base();
        mIntent = new Intent();
        mIntent.putExtra("step", step);
    }

    private void get_data_base() {
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                clazz = get_data();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                loading_view.hide();
            }
        }.execute();
    }

    private void bind_views() {
        btn_cancel.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    protected abstract Clazz get_data();

    protected void build_view() {
        build_actionbar();
        build_edit_text_write();
    }

    private void build_edit_text_write() {
        et_write.setHint(get_edit_text_write_hint_res_id());
        et_write.setText(get_default_write_text());
    }

    private void build_actionbar() {
        fatv_write_actionbar_icon.setText(get_title_icon_res_id());
        tv_write_actionbar_title.setText(get_title_res_id());
        btn_delete.setText(get_actionbar_button_text_res_id());
        if (!is_actionbar_button_show())
            btn_delete.setVisibility(View.INVISIBLE);

    }

    protected void finish_with_result() {
        mIntent.putExtra("step", step);
        mIntent.putExtra("changed", true);
        finish();
    }

    @Override
    public void finish() {
        this.setResult(CODE_STEP_ACTIONS, mIntent);
        super.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                System.out.println("btn_cancel");
                finish();
                return;
            case R.id.btn_save:
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
                save_base();
                return;
            case R.id.btn_delete:
                confirm_delete();
                break;
        }
    }

    private void save_base() {
        new KCAsyncTask<Void>(this) {
            @Override
            protected void onPreExecute() throws Exception {
                btn_save.setEnabled(false);
            }

            @Override
            public Void call() throws Exception {
                save();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                finish_with_result();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                btn_save.setEnabled(true);
            }
        }.execute();
    }

    private void confirm_delete() {
        new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您确定要删除？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        destroy_base();
                    }
                }).create().show();
        return;
    }

    private void destroy_base() {
        new KCAsyncTask<Void>(this) {
            @Override
            protected void onPreExecute() throws Exception {
                btn_delete.setEnabled(false);
            }

            @Override
            public Void call() throws Exception {
                destroy();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                finish_with_result();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                btn_delete.setEnabled(true);
            }
        }.execute();
    }

    protected abstract void save();

    protected String get_default_write_text() {
        try {
            return clazz == null ? "" : (String) clazz.getClass().getMethod("get_content", new Class[]{}).invoke(clazz, new Object[]{});
        } catch (Exception ex) {
            return "";
        }
    }

    ;

    protected abstract boolean is_actionbar_button_show();

    protected abstract int get_edit_text_write_hint_res_id();

    protected abstract int get_actionbar_button_text_res_id();

    protected abstract int get_title_res_id();

    protected abstract int get_title_icon_res_id();

    protected abstract void destroy();
}
