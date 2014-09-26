package com.mindpin.kc_android.adapter;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.IStep;



public class MyStepListAdapter extends KnowledgeBaseAdapter<IStep> {


    final Activity activity;


    public MyStepListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.my_step_list_item, null);
    }


    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.step_title_view = (TextView) view.findViewById(R.id.step_title_view);

        return view_holder;
    }


    @Override
    public void fill_with_data(BaseViewHolder holder, IStep item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.step_title_view.setText(item.get_title());


    }


    private class ViewHolder implements BaseViewHolder {
        TextView step_title_view;
    }
}