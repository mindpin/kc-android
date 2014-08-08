package com.mindpin.kc_android.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.adapter.base.KnowledgeBaseAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by fushang318 on 14-1-13.
 */
public class UIMockTutorialAdapter extends KnowledgeBaseAdapter<ITutorial> {
    public UIMockTutorialAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public View inflate_view() {
        return inflate(R.layout.knowledge_net_tutorial_list_item, null);
    }

    @Override
    public BaseViewHolder build_view_holder(View view) {
        ViewHolder view_holder = new ViewHolder();
        view_holder.iv_cover = (ImageView) view.findViewById(R.id.iv_cover);
        view_holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
        view_holder.tv_description = (TextView) view.findViewById(R.id.tv_description);
        view_holder.tv_is_learned = (TextView) view.findViewById(R.id.tv_is_learned);
        return view_holder;
    }

    @Override
    public void fill_with_data(BaseViewHolder holder, ITutorial item, int position) {
        ViewHolder view_holder = (ViewHolder) holder;
//        view_holder.iv_cover.setImageURI(Uri.parse(item.get_icon_url()));
        view_holder.tv_title.setText(item.get_title());
        view_holder.tv_description.setText(item.get_desc());
        view_holder.tv_is_learned.setText("否");
        URL newurl = null;
        try {
            newurl = new URL(item.get_icon_url());
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            view_holder.iv_cover.setImageBitmap(mIcon_val);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private class ViewHolder implements BaseViewHolder {
        ImageView iv_cover;
        TextView tv_title;
        TextView tv_description;
        TextView tv_is_learned;
    }
}
