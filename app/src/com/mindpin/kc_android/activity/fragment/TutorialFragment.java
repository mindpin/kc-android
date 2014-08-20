package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.github.destinyd.FlipBriefLayout;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.TutorialStepListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dd on 14-8-11.
 */
public class TutorialFragment extends KnowledgeBaseFragment {

    private static final String TAG = "TutorialActivity";
    // brief
    protected ListView lv_list_brief;
    protected TextView tv_description_brief;
    protected ImageView iv_cover;

    //detail
    protected TextView tv_title_detail;
    protected TextView tv_description_detail;
    protected Button btn_next_step;
    protected LinearLayout ll_steps;

    View brief;
    View detail;
    ITutorial tutorial;
    FlipBriefLayout kcFlip;
    KnowledgeNetPointListAdapter adapter_brief;
    TutorialStepListAdapter adapter_detail;
    List<IKnowledgePoint> point_list;
    LoadingView loading_view;
    String tutorial_id;
    IStep step_now;
    String id_next_step;
    List<Button> list_buttons = new ArrayList<Button>();

    private View current_view;
    private Activity activity;
    private LoadingView loading_view_detail;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.tutorial, null);
        tutorial_id = activity.getIntent().getStringExtra("tutorial_id");
        kcFlip = (FlipBriefLayout) current_view.findViewById(R.id.kcflip);
        loading_view = (LoadingView) current_view.findViewById(R.id.loading_view);
        return current_view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        tutorial_id = args.getString("tutorial_id");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        get_and_add_layouts_to_flip();
        find_views();
        bind_listener();
        get_datas();
    }

    private void bind_listener() {
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_next_step(step_now.get_next_id());
            }
        });
    }

    private void datas_to_brief() {
        tv_description_brief.setText(tutorial.get_desc());
        ImageLoader.getInstance().displayImage(tutorial.get_icon_url(), iv_cover);
        adapter_brief = new KnowledgeNetPointListAdapter(activity);
        adapter_brief.add_items(point_list);
        lv_list_brief.setAdapter(adapter_brief);
        lv_list_brief.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                IKnowledgePoint knowledgePoint = (IKnowledgePoint) parent.getItemAtPosition(position);
                String knowledge_point_id = knowledgePoint.get_id();

                Bundle args = new Bundle();
                args.putString("knowledge_point_id", knowledge_point_id);
                KnowledgePointFragment knowledge_net_fragment = new KnowledgePointFragment();
                switch_fragment(knowledge_net_fragment, args);
            }
        });
    }

    private void datas_to_detail() {
        tv_title_detail.setText(tutorial.get_title());
        tv_description_detail.setText(tutorial.get_desc());
        add_step(step_now);
    }

    private void add_step(IStep step) {
        LinearLayout ll_step = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.step_list_item, null);
        if (step != null) {
            ((TextView) ll_step.findViewById(R.id.tv_title)).setText(step.get_title());
            ((TextView) ll_step.findViewById(R.id.tv_description)).setText(step.get_desc());
            if (step.get_continue_type() == IStep.ContinueType.SELECT) {
                IStep.ISelect select = step.get_select();

                TextView tv_question = new TextView(activity);
                tv_question.setText(select.get_question());
                ll_step.addView(new TextView(activity));

                List<IStep.ISelectOption> select_options = select.get_select_options();
                for (IStep.ISelectOption option : select_options) {
                    Button button = new Button(activity);
                    button.setText(option.get_title());
                    button.setTag(option.get_next_step_id());
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            get_next_step((String) v.getTag());
                            set_buttons_enable(false);
                        }
                    });
                    list_buttons.add(button);
                    ll_step.addView(button);
                }
            }
            ll_steps.addView(ll_step);
            if (step.get_continue_type() == IStep.ContinueType.END || step.get_continue_type() == IStep.ContinueType.SELECT)
                btn_next_step.setVisibility(View.GONE);
            else
                btn_next_step.setVisibility(View.VISIBLE);
        }
    }

    private void set_buttons_enable(boolean b) {
        for(Button button : list_buttons){
            button.setEnabled(b);
        }
    }

    private void get_next_step(String id) {
        id_next_step = id;
        new KCAsyncTask<IStep>(activity) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view_detail.show();
            }

            @Override
            public IStep call() throws Exception {
                IStep step = DataProvider.get_step(id_next_step);
                return step;
            }

            @Override
            protected void onSuccess(IStep step) throws Exception {
                System.out.println(step.get_title());
                add_step(step);
                step_now = step;
                loading_view_detail.hide();
                for (Button button : list_buttons) {
                    button.setVisibility(View.GONE);
                }
                list_buttons.clear();
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                super.onException(e);
                loading_view_detail.hide();
                set_buttons_enable(true);
            }
        }.execute();
    }


    private void get_datas() {
        new KCAsyncTask<Void>(activity) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                tutorial = DataProvider.get_tutorial(tutorial_id);
                point_list = tutorial.get_related_knowledge_point_list();
                step_now = tutorial.get_first_step();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                System.out.println(tutorial.get_title());
                datas_to_brief();
                datas_to_detail();
                loading_view.hide();
            }
        }.execute();
    }

    private void get_and_add_layouts_to_flip() {
        brief = LayoutInflater.from(activity).inflate(R.layout.tutorial_brief, null);
        detail = LayoutInflater.from(activity).inflate(R.layout.tutorial_detail, null);
        kcFlip.set_brief_view(brief);
        kcFlip.set_detail_view(detail);
    }

    private void find_views() {
        find_brief_views();
        find_detail_views();
    }

    private void find_detail_views() {
        tv_title_detail = (TextView) detail.findViewById(R.id.tv_title_detail);
        tv_description_detail = (TextView) detail.findViewById(R.id.tv_description_detail);
        btn_next_step = (Button) detail.findViewById(R.id.btn_next_step);
        ll_steps = (LinearLayout) detail.findViewById(R.id.ll_steps);
        loading_view_detail = (LoadingView) detail.findViewById(R.id.loading_view_detail);
    }

    private void find_brief_views() {
        lv_list_brief = (ListView) brief.findViewById(R.id.lv_list_brief);
        tv_description_brief = (TextView) brief.findViewById(R.id.tv_description_brief);
        iv_cover = (ImageView) brief.findViewById(R.id.iv_cover);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        kcFlip.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        kcFlip.onPause();
//    }
}