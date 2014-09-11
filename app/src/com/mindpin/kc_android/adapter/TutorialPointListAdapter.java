package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.mindpin.android.kcroundprogressbar.KCRoundProgressBar;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;

/**
 * Created by fushang318 on 14-1-13.
 */
public class TutorialPointListAdapter extends KnowledgeBaseAdapter<IKnowledgePoint> {
    final Activity activity;
    public TutorialPointListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.tutorial_point_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.rpb_round = (KCRoundProgressBar) view.findViewById(R.id.rpb_round);
        view_holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, IKnowledgePoint item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.rpb_round.set_current(100);
//        view_holder.rpb_round.set_thickness(1f / 3f); //环所在位置没设置好，被切割
        view_holder.rpb_round.set_fg_color(Color.parseColor(item.get_color()));
        view_holder.tv_name.setText(item.get_name());
    }

    private class ViewHolder implements BaseViewHolder {
        KCRoundProgressBar rpb_round;
        TextView tv_name;
    }
}
