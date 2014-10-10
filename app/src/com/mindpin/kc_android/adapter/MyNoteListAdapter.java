package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.INote;
import com.nostra13.universalimageloader.core.ImageLoader;


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

        view_holder.step_title_view = (TextView) view.findViewById(R.id.step_title_view);
        view_holder.time_view = (TextView) view.findViewById(R.id.time_view);
        view_holder.tutorial_cover = (ImageView) view.findViewById(R.id.tutorial_cover);

        return view_holder;
    }


    @Override
    public void fill_with_data(BaseViewHolder holder, INote item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.note_content_view.setText(item.get_content());

        view_holder.step_title_view.setText(item.get_step().get_title());
        view_holder.time_view.setText(item.get_created_at());
        ImageLoader.getInstance().displayImage(item.get_tutorial_icon_uri(), view_holder.tutorial_cover);

    }


    private class ViewHolder implements BaseViewHolder {
        TextView note_content_view;

        TextView time_view;
        ImageView tutorial_cover;
        TextView step_title_view;
    }
}