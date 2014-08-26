package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.utils.BaseUtils;
import com.mindpin.kc_android.utils.UiFont;
import com.nostra13.universalimageloader.core.ImageLoader;

import it.sephiroth.android.library.widget.AbsHListView;

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

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels * 45 / 100;
        int height = width * 2;
        AbsHListView.LayoutParams lp = new AbsHListView.LayoutParams(width, AbsHListView.LayoutParams.WRAP_CONTENT);
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
        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, ITutorial item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
        view_holder.tutorial_title_tv.setText(item.get_title());
        view_holder.tutorial_desc_tv.setText(item.get_desc());
        ImageLoader.getInstance().displayImage(item.get_icon_url(), view_holder.tutorial_icon_iv);
    }

    private class ViewHolder implements BaseViewHolder {
        TextView tutorial_title_tv;
        ImageView tutorial_icon_iv;
        TextView tutorial_desc_tv;
        TextView tutorial_learned_tv;
    }
}
