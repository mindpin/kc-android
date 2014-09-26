package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.INote;


public class MyNoteListAdapter extends KnowledgeBaseAdapter<INote> {


    final Activity activity;


    public MyNoteListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.my_note_list_item, null);
    }


    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.note_content_view = (TextView) view.findViewById(R.id.note_content_view);

        return view_holder;
    }


    @Override
    public void fill_with_data(BaseViewHolder holder, INote item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.note_content_view.setText(item.get_content());


    }


    private class ViewHolder implements BaseViewHolder {
        TextView note_content_view;
    }
}