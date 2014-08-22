package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;

/**
 * Created by fushang318 on 2014/8/22.
 */
public class TopicTutorialListAdapter extends KnowledgeBaseAdapter<ITutorial> {

    public TopicTutorialListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.topic_tutorial_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.tutorial_title_tv = (TextView) view.findViewById(R.id.tutorial_title_tv);
        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, ITutorial item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.tutorial_title_tv.setText(item.get_title());
    }

    private class ViewHolder implements BaseViewHolder {
        TextView tutorial_title_tv;
    }
}
