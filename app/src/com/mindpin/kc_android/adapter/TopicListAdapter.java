package com.mindpin.kc_android.adapter;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.nostra13.universalimageloader.core.ImageLoader;


public class TopicListAdapter extends KnowledgeBaseAdapter<ITopic> {


    final Activity activity;


    public TopicListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.topic_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.topic_title_view = (TextView) view.findViewById(R.id.topic_title);
        view_holder.topic_desc_view = (TextView) view.findViewById(R.id.topic_desc);
        view_holder.topic_tutorial_count_view =
                (TextView) view.findViewById(R.id.topic_tutorial_count);
        view_holder.topic_icon_view = (ImageView) view.findViewById(R.id.topic_icon);
        return view_holder;
    }


    @Override
    public void fill_with_data(BaseViewHolder holder, ITopic item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.topic_title_view.setText(item.get_title());
        view_holder.topic_desc_view.setText(item.get_desc());
        view_holder.topic_tutorial_count_view.setText(item.get_tutorial_count());


        ImageLoader.getInstance().displayImage(item.get_icon_url(), view_holder.topic_icon_view);
    }


    private class ViewHolder implements BaseViewHolder {
        TextView topic_title_view;
        TextView topic_desc_view;
        TextView topic_tutorial_count_view;
        ImageView topic_icon_view;
    }
}