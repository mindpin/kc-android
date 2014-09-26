package com.mindpin.kc_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.destinyd.kcvideoview.widget.KCVideoView;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.models.http.Step;
import com.mindpin.kc_android.models.http.Tutorial;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.BaseUtils;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.mindpin.kc_android.widget.FontAwesomeButton;
import com.mindpin.kc_android.widget.FontAwesomeTextView;
import com.mindpin.kc_android.widget.ObservableScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dd on 14-9-2.
 */
public class LearnActivity extends KnowledgeBaseActivity implements View.OnClickListener {
    @InjectExtra("tutorial")
    Tutorial tutorial;

    @InjectView(R.id.ll_steps)
    LinearLayout ll_steps;

    @InjectView(R.id.loading_view)
    LoadingView loading_view;

    @InjectView(R.id.tv_title)
    TextView tv_title;
    @InjectView(R.id.fatv_back)
    FontAwesomeTextView fatv_back;
    @InjectView(R.id.sv_steps)
    ObservableScrollView sv_steps;

    private IStep step_now;
    private String id_next_step;
    private Button btn_next_step = null;
    private LinearLayout.LayoutParams margin_bottom_10dp;
    private float display_width_dp;
    private float imageview_width_dp;
    private Animation mHiddenAction;
    private Animation mShowAction;
    private Button last_btn_next_step;
    private int learned_step_count;
    private int index = 1;
    private List<IStep> steps = new ArrayList<IStep>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_activity);

        init();
    }

    private void init() {
        init_views();
        init_params();
        get_datas();
    }

    private void init_views() {
        fatv_back.setOnClickListener(this);
        tv_title.setText(tutorial.get_title());
    }

    private void init_params() {
        display_width_dp = BaseUtils.get_screen_size().width_dp;
        imageview_width_dp = display_width_dp - 5*2 - 10*2;

        margin_bottom_10dp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        margin_bottom_10dp.setMargins(0, 0, 0, 10);

        mHiddenAction = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
        mHiddenAction.setAnimationListener(
                new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ((ViewGroup)last_btn_next_step.getParent()).removeView(last_btn_next_step);
//                        last_btn_next_step.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                }
        );
        mShowAction = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
    }

    private void steps_to_views() {
        for(int i=0; i < learned_step_count; i++)
            add_step(steps.get(i));
    }

    private void add_step(final IStep istep) {
        if (istep != null) {
            LinearLayout ll_step = (LinearLayout) getLayoutInflater().inflate(R.layout.learn_step_list_item, null);
            TextView tv_step_title = (TextView) ll_step.findViewById(R.id.tv_step_title);
            LinearLayout ll_step_blocks = (LinearLayout) ll_step.findViewById(R.id.ll_step_blocks);
            if("".equals(istep.get_title())) {
                tv_step_title.setVisibility(View.GONE);
            }
            else{
                tv_step_title.setText(istep.get_title());
            }
            List<IStep.IContentBlock> blocks = istep.get_blocks();
            for(IStep.IContentBlock block : blocks){
                if(block.get_kind() == IStep.IContentBlock.ContentKind.TEXT){
                    TextView tv_block = (TextView) getLayoutInflater().inflate(R.layout.learn_step_list_item_block_text, null);
                    tv_block.setText(block.get_content());
                    ll_step_blocks.addView(tv_block);
                } else if(block.get_kind() == IStep.IContentBlock.ContentKind.IMAGE){
                    ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.learn_step_list_item_block_imageview, null);
                    ll_step_blocks.addView(imageView, new LinearLayout.LayoutParams(
                            BaseUtils.dp_to_px(imageview_width_dp),
                            BaseUtils.dp_to_px(block.get_height().floatValue() * imageview_width_dp / block.get_width().floatValue())
                    ));
                    ImageLoader.getInstance().displayImage(block.get_url(), imageView);
                } else if(block.get_kind() == IStep.IContentBlock.ContentKind.VIDEO){
                    KCVideoView kcVideoView = (KCVideoView) getLayoutInflater().inflate(R.layout.learn_step_list_item_block_videoview, null);
                    kcVideoView.load_url(block.get_url());
                    kcVideoView.set_seconds(block.get_duration());
                    ll_step_blocks.addView(kcVideoView, new LinearLayout.LayoutParams(
                            BaseUtils.dp_to_px(imageview_width_dp), BaseUtils.dp_to_px(3f * imageview_width_dp / 4f)
                            )
                    );
                }
            }

            LinearLayout actions = (LinearLayout) getLayoutInflater().inflate(R.layout.learn_step_list_item_actions, null);
            ll_step_blocks.addView(actions, margin_bottom_10dp);
            //todo for action actioned
            actions.findViewById(R.id.fabtn_note).setTag(istep);
            actions.findViewById(R.id.fabtn_question).setTag(istep);
            actions.findViewById(R.id.fabtn_hard_point).setTag(istep);
            refresh_step_actions(istep, actions);

            actions.findViewById(R.id.fabtn_note).setOnClickListener(this);
            actions.findViewById(R.id.fabtn_question).setOnClickListener(this);
            actions.findViewById(R.id.fabtn_hard_point).setOnClickListener(this);

            if (istep.get_continue_type() == IStep.ContinueType.STEP){
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.learn_step_list_item_next, null);
                btn_next_step = (Button) linearLayout.findViewById(R.id.btn_next_step);
                btn_next_step.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_next_step.setEnabled(false);
                        get_next_step(istep.get_next_id());
                    }
                });
                ll_step_blocks.addView(linearLayout);
                ll_steps.addView(ll_step, margin_bottom_10dp);
            } else if (istep.get_continue_type() == IStep.ContinueType.END) {
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.learn_step_list_item_end, null);
                ll_step_blocks.addView(linearLayout);
                ll_steps.addView(ll_step);
            }
            if(ll_steps.getChildCount() > 1) { // cas already add
                ll_step.startAnimation(mShowAction);
            }
            ll_steps.requestLayout();
        }
    }

    private void refresh_step_actions(IStep istep, LinearLayout actions) {
        if(istep.has_note())
            ((FontAwesomeButton)actions.findViewById(R.id.fabtn_note))
                    .setTextColor(getResources().getColor(R.color.learn_step_action_actioned_color));
        if(istep.has_question())
            ((FontAwesomeButton)actions.findViewById(R.id.fabtn_question))
                    .setTextColor(getResources().getColor(R.color.learn_step_action_actioned_color));
        if(istep.is_hard()) {
            ((FontAwesomeButton) actions.findViewById(R.id.fabtn_hard_point))
                    .setTextColor(getResources().getColor(R.color.learn_step_action_is_hard_point_color));
        } else {
            ((FontAwesomeButton) actions.findViewById(R.id.fabtn_hard_point))
                    .setTextColor(getResources().getColor(R.color.learn_step_action_no_actioned_color));
        }
    }

    private void get_next_step(String id) {
        id_next_step = id;
        new KCAsyncTask<IStep>(this) {
            @Override
            public IStep call() throws Exception {
                IStep step = DataProvider.get_step(id_next_step);
                step.do_learn();
                return step;
            }

            @Override
            protected void onSuccess(IStep step) throws Exception {
                if(btn_next_step != null){
                    last_btn_next_step = btn_next_step;
                    last_btn_next_step.startAnimation(mHiddenAction);
                    btn_next_step = null;
                }
                add_step(step);
                step_now = step;
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
            }
        }.execute();
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
                step_now.do_learn();
                steps.add(step_now);
                for(int i = index; i<learned_step_count; i++) {
                    learned_step_count = tutorial.get_learned_step_count();
                    if(!step_now.is_end()) {
                        step_now = DataProvider.get_step(step_now.get_next_id());
                        steps.add(step_now);
                    }
                }
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                steps_to_views();
                loading_view.hide();
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.fatv_back:
                finish();
                break;
            case R.id.fabtn_note:
                intent = new Intent(this, NotesActivity.class);
                intent.putExtra("step", (Step)v.getTag());
                startActivity(intent);
                break;
            case R.id.fabtn_question:
                intent = new Intent(this, QuestionActivity.class);
                intent.putExtra("step", (Step)v.getTag());
                startActivity(intent);
                break;
            case R.id.fabtn_hard_point:
                System.out.println("hard point");
                Step s = (Step) v.getTag();
                if(s.is_hard()) {
                    System.out.println("unset_hard");
                    s.unset_hard();
                    System.out.println(s.is_hard() ? "is hard" : "not hard");
                } else {
                    System.out.println("set_hard");
                    s.set_hard();
                    System.out.println(s.is_hard() ? "is hard" : "not hard");
                }
                v.setTag(s);
                refresh_step_actions(s, (LinearLayout) v.getParent());
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){   //確定按下退出鍵and防止重複按下退出鍵
            if(sv_steps.getScrollY() > ll_steps.getPaddingTop()){
                View view = null;
                int height = ll_steps.getPaddingTop();
                for(int i=0; i< ll_steps.getChildCount(); i++) {
                    view = ll_steps.getChildAt(i);
                    if(height + view.getHeight()   >= sv_steps.getScrollY()) {
                        sv_steps.smoothScrollTo(0, height);
                        return true;
                    }
                    else
                        height += view.getHeight();
                }
                sv_steps.smoothScrollTo(0, ll_steps.getPaddingTop());
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
