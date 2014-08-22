package com.mindpin.kc_android.utils.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.utils.ImageTools;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by Administrator on 14-1-13.
 */
public class AvatarImageView extends ImageView {
    private boolean loaded = false;

    public AvatarImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!loaded) load();
    }

    private void load() {
        if (isInEditMode()) return;
        loaded = true;

//        String url;
//        User user = User.current();
//        if(user != null) {
//            url = user.avatar_url;
//        }
//        else {
//        url = "http://www.funcage.com/photos/funcage-eqhsd.jpg";
//          url = "drawable://" + R.drawable.avatar_default;
//        }
        String url = "drawable://" + R.drawable.avatar_default;

        ImageLoader.getInstance().loadImage(url, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Bitmap b = ImageTools.createBitmapBySize(loadedImage, getWidth(), getHeight());
                CircleAvatarDrawable d = new CircleAvatarDrawable(b);
                setBackgroundDrawable(d);
            }
        });
    }
}
