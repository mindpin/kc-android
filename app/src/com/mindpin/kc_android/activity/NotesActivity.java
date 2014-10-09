package com.mindpin.kc_android.activity;

import android.view.View;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseWriteActivity;
import com.mindpin.kc_android.models.interfaces.INote;

/**
 * Created by dd on 14-9-25.
 */
public class NotesActivity extends KnowledgeBaseWriteActivity<INote> implements View.OnClickListener {
    @Override
    protected INote get_data() {
        return step.get_note();
    }

    @Override
    protected int get_edit_text_write_hint_res_id() {
        return R.string.notes_edit_text_hint;
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
    protected void destroy() {
        step.destroy_note();
    }

    @Override
    protected void save() {
        if (step.has_note()) {
            step.edit_note(et_write.getText().toString());
        } else {
            step.create_note(et_write.getText().toString());
        }
    }
}