package com.mindpin.kc_android.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;

import java.util.List;



public class KnowledgeNetListAdapter extends KnowledgeBaseAdapter<IKnowledgeNet> {


    final Activity activity;
    public KnowledgeNetListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.knowledge_net_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.name_view = (TextView) view.findViewById(R.id.knowledge_net_list_name);
        view_holder.point_count_view =
                (TextView) view.findViewById(R.id.knowledge_net_list_point_count);
        return view_holder;
    }


    @Override
    public void fill_with_data(BaseViewHolder holder, IKnowledgeNet item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.name_view.setText(item.get_name());
        view_holder.point_count_view.setText(Integer.toString(item.get_point_count()));
    }


    private class ViewHolder implements BaseViewHolder {
        TextView name_view;
        TextView point_count_view;
    }
}