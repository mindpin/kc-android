package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;

/**
 * Created by fushang318 on 14-1-13.
 */
public class KnowledgeNetPointListAdapter extends KnowledgeBaseAdapter<IKnowledgePoint> {
    final Activity activity;
    public KnowledgeNetPointListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.knowledge_net_point_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, IKnowledgePoint item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.tv_title.setText(item.get_name());
    }

    private class ViewHolder implements BaseViewHolder {
        TextView tv_title;
    }
}
