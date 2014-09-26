package com.mindpin.kc_android.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.models.http.Step;
import com.mindpin.kc_android.widget.FontAwesomeTextView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by fushang318 on 2014/8/14.
 */
public abstract class KnowledgeBaseWriteActivity extends KnowledgeBaseActivity{
    @InjectExtra("step")
    protected Step step;
    @InjectView(R.id.fatv_write_actionbar_icon)
    FontAwesomeTextView fatv_write_actionbar_icon;
    @InjectView(R.id.tv_write_actionbar_title)
    TextView tv_write_actionbar_title;
    @InjectView(R.id.btn_write_actionbar)
    Button btn_write_actionbar;

    @InjectView(R.id.btn_save)
    Button btn_save;
    @InjectView(R.id.btn_cancel)
    Button btn_cancel;
    @InjectView(R.id.et_write)
    EditText et_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
        build_view();
    }

    private void build_view() {
        build_actionbar();
        build_bottom();
        build_edit_text_write();
    }

    private void build_edit_text_write() {
        et_write.setHint(get_edit_text_write_hint_res_id());
    }

    private void build_bottom() {
        btn_save.setOnClickListener(get_button_save_on_click_listener(btn_save));
        btn_cancel.setOnClickListener(get_button_cancel_on_click_listener(btn_cancel));
    }

    private void build_actionbar() {
        fatv_write_actionbar_icon.setText(get_title_icon_res_id());
        tv_write_actionbar_title.setText(get_title_res_id());
        btn_write_actionbar.setText(get_actionbar_button_text_res_id());
        btn_write_actionbar.setOnClickListener(get_actionbar_button_on_click_listener(btn_write_actionbar));
        if(!is_actionbar_button_show())
            btn_write_actionbar.setVisibility(View.INVISIBLE);

    }

    protected abstract boolean is_actionbar_button_show();

    protected abstract int get_edit_text_write_hint_res_id();

    protected abstract View.OnClickListener get_button_cancel_on_click_listener(View btn_cancel);

    protected abstract View.OnClickListener get_button_save_on_click_listener(View btn_save);

    protected abstract View.OnClickListener get_actionbar_button_on_click_listener(View btn_write_actionbar);

    protected abstract int get_actionbar_button_text_res_id();

    protected abstract int get_title_res_id();

    protected abstract int get_title_icon_res_id();
}
