package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mindpin.android.kcroundprogressbar.KCRoundProgressBar;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.utils.BaseUtils;
import com.mindpin.kc_android.utils.UiFont;
import com.mindpin.kc_android.widget.HorizontalListView;
import com.nostra13.universalimageloader.core.ImageLoader;


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
        view_holder.tutorial_icon_iv = (ImageView) view.findViewById(R.id.tutorial_icon_iv);
        view_holder.tutorial_desc_tv = (TextView) view.findViewById(R.id.tutorial_desc_tv);
        view_holder.tutorial_learned_tv = (TextView)view.findViewById(R.id.tutorial_learned_tv);
        view_holder.tutorial_learn_progress = (KCRoundProgressBar)view.findViewById(R.id.tutorial_learn_progress);

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels * 45 / 100;
        int height = width * 2;
        HorizontalListView.LayoutParams lp = new HorizontalListView.LayoutParams(width, HorizontalListView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        RelativeLayout.LayoutParams icon_lp = new RelativeLayout.LayoutParams(
                width-BaseUtils.dp_to_px(10),
                width-BaseUtils.dp_to_px(10));
        icon_lp.topMargin = BaseUtils.dp_to_px(5);
        icon_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        icon_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        icon_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        view_holder.tutorial_icon_iv.setLayoutParams(icon_lp);

        view_holder.tutorial_learned_tv.setTypeface(UiFont.FONTAWESOME_FONT);

        view_holder.tutorial_learn_progress.set_fg_color(Color.parseColor("#4cc85e"));
        view_holder.tutorial_learn_progress.set_text_size_dp(9);
        view_holder.tutorial_learn_progress.set_thickness(0.2f);
        view_holder.tutorial_learn_progress.set_internal_bg_color(Color.parseColor("#E4F7E7"));
        view_holder.tutorial_learn_progress.set_text_color(Color.parseColor("#333333"));
        view_holder.tutorial_learn_progress.set_text_show_type(KCRoundProgressBar.TextShowType.PERCENTAGE);
        view_holder.tutorial_learn_progress.set_text_display(true);
        view_holder.tutorial_learn_progress.set_border_display(false);

        view_holder.tutorial_learn_progress.setVisibility(View.GONE);
        view_holder.tutorial_learned_tv.setVisibility(View.GONE);

        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, ITutorial item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.tutorial_title_tv.setText(item.get_title());
        view_holder.tutorial_desc_tv.setText(item.get_desc());
        ImageLoader.getInstance().displayImage(item.get_icon_url(), view_holder.tutorial_icon_iv);

        if(item.get_step_count() == item.get_learned_step_count()){
            view_holder.tutorial_learned_tv.setVisibility(View.VISIBLE);
        }else{
            view_holder.tutorial_learn_progress.set_max(item.get_step_count());
            view_holder.tutorial_learn_progress.set_min(0);
            view_holder.tutorial_learn_progress.set_current(item.get_learned_step_count());
            view_holder.tutorial_learn_progress.setVisibility(View.VISIBLE);
        }

    }

    private class ViewHolder implements BaseViewHolder {
        TextView tutorial_title_tv;
        ImageView tutorial_icon_iv;
        TextView tutorial_desc_tv;
        TextView tutorial_learned_tv;
        KCRoundProgressBar tutorial_learn_progress;
    }
}
