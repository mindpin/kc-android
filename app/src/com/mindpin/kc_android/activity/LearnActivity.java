package com.mindpin.kc_android.activity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.github.destinyd.kcvideoview.widget.KCVideoView;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.models.http.Tutorial;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.BaseUtils;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dd on 14-9-2.
 */
public class LearnActivity extends KnowledgeBaseActivity {
    @InjectExtra("tutorial")
    Tutorial tutorial;

    @InjectView(R.id.iv_cover)
    ImageView iv_cover;
    @InjectView(R.id.tv_title)
    TextView tv_title;
    @InjectView(R.id.tv_desc)
    TextView tv_desc;
    @InjectView(R.id.ll_steps)
    LinearLayout ll_steps;

    @InjectView(R.id.loading_view)
    LoadingView loading_view;

    private IStep step_now;
    private String id_next_step;
    private List<RadioButton> list_radios = new ArrayList<RadioButton>();
    private Button btn_next_step = null;
    private static int dp20 = BaseUtils.dp_to_px(10);
    private static int dp5 = BaseUtils.dp_to_px(2.5f);
    private LinearLayout.LayoutParams layoutParams, layoutParamsHeightWrap20dp, layoutParamsHeightWrap5dp;
    private LinearLayout.LayoutParams layoutParamsHeight200dp;
    RadioGroup radioGroup;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_activity);

        init();
    }

    private void init() {
        init_params();
        get_datas();
    }

    private void init_params() {
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsHeightWrap20dp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsHeightWrap20dp.setMargins(dp20, dp20, dp20, 0);
        layoutParamsHeightWrap5dp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsHeightWrap5dp.setMargins(dp5, dp5, dp5, 0);
        layoutParamsHeight200dp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp20 * 10);
        layoutParamsHeight200dp.setMargins(dp20, dp20, dp20, 0);
    }

    private void datas_to_views() {
        tutorial_to_views();
        step_to_views();
    }

    private void step_to_views() {
        add_step(step_now);
    }

    private void add_step(final IStep step) {
        if (step != null) {
            LinearLayout ll_step = new LinearLayout(this);
            ll_step.setOrientation(LinearLayout.VERTICAL);
            TextView tv_title = get_tv_title(step);
            ll_step.addView(tv_title, layoutParamsHeightWrap20dp);
            List<IStep.IContentBlock> blocks = step.get_blocks();
            for(IStep.IContentBlock block : blocks){
                if(block.get_kind() == IStep.IContentBlock.ContentKind.TEXT){
                    TextView tv_block = get_tv_block(block.get_content());
                    ll_step.addView(tv_block, layoutParamsHeightWrap20dp);
                } else if(block.get_kind() == IStep.IContentBlock.ContentKind.IMAGE){
                    ImageView imageView = new ImageView(this);
                    ll_step.addView(imageView, layoutParamsHeightWrap20dp);
                    ImageLoader.getInstance().displayImage(block.get_url(), imageView);
                } else if(block.get_kind() == IStep.IContentBlock.ContentKind.VIDEO){
                    System.out.println(block.get_url());
                    KCVideoView kcVideoView = new KCVideoView(this);
                    kcVideoView.setBackgroundColor(0xffff0000);
                    ll_step.addView(kcVideoView, layoutParamsHeight200dp);
                    kcVideoView.load_url(block.get_url());
//                    kcVideoView.load(block.get_url());
                }
            }
            if (step.get_continue_type() == IStep.ContinueType.SELECT) {
                System.out.println("SELECT");
                IStep.ISelect select = step.get_select();

                TextView tv_question = new TextView(this);
                tv_question.setText(select.get_question());
                tv_question.setTypeface(tv_question.getTypeface(), Typeface.BOLD);
                ll_step.addView(tv_question, layoutParamsHeightWrap20dp);

                radioGroup = new RadioGroup(this);
                List<IStep.ISelectOption> select_options = select.get_select_options();
                for (IStep.ISelectOption option : select_options) {
                    RadioButton radio_button = new RadioButton(this);
                    radio_button.setText(option.get_text());
                    radio_button.setTag(option.get_next_step_id());
                    radio_button.setBackgroundColor(0xff999999);
                    radioGroup.addView(radio_button, layoutParams);
                    ((LinearLayout.LayoutParams)radio_button.getLayoutParams()).setMargins(0, dp5, 0, 0);
                    list_radios.add(radio_button);
                }
                ll_step.addView(radioGroup, layoutParamsHeightWrap20dp);

                btn_next_step = new Button(this);
                btn_next_step.setText("确定");
                btn_next_step.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    if(radioGroup.getCheckedRadioButtonId() > -1) {
                        btn_next_step.setEnabled(false);
                        set_radios_enable(false);
                        get_next_step(findViewById(radioGroup.getCheckedRadioButtonId()).getTag().toString());
                    }
                    else{
                        Toast.makeText(LearnActivity.this, "你还未做选择", Toast.LENGTH_LONG).show();
                    }
                    }
                });

                ll_step.addView(btn_next_step, layoutParamsHeightWrap20dp);
            }
            else if (step.get_continue_type() == IStep.ContinueType.STEP){
                System.out.println("STEP");
                btn_next_step = new Button(this);
                btn_next_step.setText("继续阅读");
                btn_next_step.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_next_step.setEnabled(false);
                        get_next_step(step.get_next_id());
                    }
                });
                ll_step.addView(btn_next_step, layoutParamsHeightWrap20dp);
            }

            LinearLayout dividing = (LinearLayout)getLayoutInflater().inflate(R.layout.include_dotted_dividing_line, null);
            ll_step.addView(dividing, layoutParamsHeightWrap5dp);

            ll_steps.addView(ll_step, layoutParamsHeightWrap20dp);

            if (step.get_continue_type() == IStep.ContinueType.END) {
                System.out.println("END");
                TextView textView = new TextView(this);
                textView.setText("教程结束啦");
                textView.setBackgroundColor(0xff229999);
                textView.setPadding(dp20, dp20, dp20, dp20);
                ll_steps.addView(textView, layoutParams);
                ((LinearLayout.LayoutParams)textView.getLayoutParams()).setMargins(0, dp20, 0, 0);
            }
        }
    }

    private TextView get_tv_block(String content) {
        TextView textView = new TextView(this);
        textView.setText(content);
        return textView;
    }

    private TextView get_tv_title(IStep step) {
        TextView textView = new TextView(this);
        textView.setText("> " + step.get_title());
        textView.setTypeface(null, Typeface.BOLD);
//        textView.setTextSize(BaseUtils.dp_to_px(16));
        return textView;
    }


    private void get_next_step(String id) {
        id_next_step = id;
        new KCAsyncTask<IStep>(this) {
            @Override
            public IStep call() throws Exception {
                IStep step = DataProvider.get_step(id_next_step);
                return step;
            }

            @Override
            protected void onSuccess(IStep step) throws Exception {
                if(btn_next_step != null && btn_next_step.getParent() != null)
                    ((ViewGroup)btn_next_step.getParent()).removeView(btn_next_step);
                if(radioGroup != null) {
                    int radioSelectId = radioGroup.getCheckedRadioButtonId();
                    for (int i = radioGroup.getChildCount() - 1; i >= 0; i--) {
                        View view = radioGroup.getChildAt(i);
                        if (view.getId() != radioSelectId)
                            radioGroup.removeViewAt(i);
                    }
                }
                add_step(step);
                step_now = step;
                list_radios.clear();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                set_radios_enable(true);
            }
        }.execute();
    }

    private void tutorial_to_views() {
        ImageLoader.getInstance().displayImage(tutorial.get_icon_url(), iv_cover);
        tv_title.setText(tutorial.get_title());
        tv_desc.setText(tutorial.get_desc());
    }

    private void get_datas() {
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                step_now = tutorial.get_first_step();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                datas_to_views();
                loading_view.hide();
            }
        }.execute();
    }

    private void set_radios_enable(boolean b) {
        for(RadioButton radio : list_radios){
            radio.setEnabled(b);
        }
    }
}
