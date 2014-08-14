package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.IStep;

/**
 * Created by fushang318 on 14-1-13.
 */
public class TutorialStepListAdapter extends KnowledgeBaseAdapter<IStep> {
    final Activity activity;
    public TutorialStepListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.tutorial_step_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
        view_holder.tv_description = (TextView) view.findViewById(R.id.tv_description);
        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, IStep item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.tv_title.setText(item.get_title());
        view_holder.tv_description.setText(item.get_desc());
    }

    private class ViewHolder implements BaseViewHolder {
        TextView tv_title;
        TextView tv_description;
    }
}
