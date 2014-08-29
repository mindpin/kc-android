package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mindpin.android.kcroundprogressbar.KCRoundProgressBar;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.utils.UiFont;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by fushang318 on 14-1-13.
 */
public class TutorialTutorialListAdapter extends KnowledgeBaseAdapter<ITutorial> {
    final Activity activity;
    public TutorialTutorialListAdapter(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.tutorial_tutorial_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.iv_cover = (ImageView) view.findViewById(R.id.iv_cover);
        view_holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
        view_holder.tutorial_learned_tv = (TextView)view.findViewById(R.id.tutorial_learned_tv);
        view_holder.tutorial_learned_tv.setTypeface(UiFont.FONTAWESOME_FONT);
        view_holder.tutorial_learn_progress = (KCRoundProgressBar) view.findViewById(R.id.tutorial_learn_progress);
        view_holder.tutorial_learn_progress.set_fg_color(Color.parseColor("#4CC85E"));
        view_holder.tutorial_learn_progress.set_bg_color(Color.parseColor("#E4F7E7"));
        view_holder.tutorial_learn_progress.set_text_size_dp(9);
        view_holder.tutorial_learn_progress.set_thickness(0.2f);
        view_holder.tutorial_learn_progress.set_internal_bg_color(Color.parseColor("#E4F7E7"));
        view_holder.tutorial_learn_progress.set_text_color(Color.parseColor("#333333"));
        view_holder.tutorial_learn_progress.set_text_show_type(KCRoundProgressBar.TextShowType.PERCENTAGE);
        view_holder.tutorial_learn_progress.set_text_display(true);

        view_holder.tutorial_learn_progress.setVisibility(View.GONE);
        view_holder.tutorial_learned_tv.setVisibility(View.GONE);

        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, ITutorial item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.tv_title.setText(item.get_title());

        ImageLoader.getInstance().displayImage(item.get_icon_url(), view_holder.iv_cover);

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
        ImageView iv_cover;
        TextView tv_title;
        TextView tutorial_learned_tv;
        KCRoundProgressBar tutorial_learn_progress;
    }
}
