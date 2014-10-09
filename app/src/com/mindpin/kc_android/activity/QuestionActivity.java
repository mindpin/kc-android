package com.mindpin.kc_android.activity;

import android.view.View;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseWriteActivity;
import com.mindpin.kc_android.models.interfaces.IQuestion;

/**
 * Created by dd on 14-9-25.
 */
public class QuestionActivity extends KnowledgeBaseWriteActivity<IQuestion> implements View.OnClickListener {
    @Override
    protected IQuestion get_data() {
        return step.get_question();
    }

    @Override
    protected int get_edit_text_write_hint_res_id() {
        return R.string.question_edit_text_hint;
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
    protected void destroy() {
        step.destroy_question();
    }

    @Override
    protected void save() {
        if (step.has_question()) {
            step.edit_question(et_write.getText().toString());
        } else {
            step.create_question(et_write.getText().toString());
        }
    }
}