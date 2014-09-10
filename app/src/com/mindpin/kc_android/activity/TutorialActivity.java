package com.mindpin.kc_android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.activity.fragment.KnowledgePointFragment;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.TutorialPointListAdapter;
import com.mindpin.kc_android.adapter.TutorialStepListAdapter;
import com.mindpin.kc_android.adapter.TutorialTutorialListAdapter;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.models.http.KnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.BaseUtils;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.mindpin.kc_android.utils.ListViewUtils;
import com.mindpin.kc_android.widget.FontAwesomeTextView;
import com.mindpin.kc_android.widget.SelectableLinearLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;


public class TutorialActivity extends KnowledgeBaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
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

//    View brief;
//    View detail;
    ITutorial tutorial;
//    FlipBriefLayout kcFlip;
    KnowledgeNetPointListAdapter adapter_brief;
    TutorialStepListAdapter adapter_detail;
    List<IKnowledgePoint> points;
    LoadingView loading_view;
//    String tutorial_id;
    IStep step_now;
    String id_next_step;
    List<Button> list_buttons = new ArrayList<Button>();
    List<ITutorial> parents;
    List<ITutorial> children;
//    private View current_view;
    private LoadingView loading_view_detail;
    private Bitmap tutorial_background;
    private ImageView iv_tutorial_background;
    private RelativeLayout rl_banner;


//    private void bind_listener() {
//        btn_next_step.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                get_next_step(step_now.get_next_id());
//            }
//        });
//    }
    private SelectableLinearLayout sll_summary;
    private ListView lv_previous;
    private ListView lv_next;
    private TutorialTutorialListAdapter adapter_children;
    private TutorialTutorialListAdapter adapter_parents;
    private TutorialPointListAdapter adapter_points;
    private TextView tv_next_none;
    private TextView tv_previous_none;
    private TextView tv_title;
    private TextView tv_desc;
    private TextView tv_learned_percent;
    private TextView tv_author;
    private FontAwesomeTextView fatv_back;
    private RelativeLayout rl_next;
    private SelectableLinearLayout sll_points;
    private SelectableLinearLayout sll_notes;
    private ListView lv_notes;
    private ListView lv_points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity);
        tutorial = (ITutorial)getIntent().getSerializableExtra("tutorial");
//        kcFlip = (FlipBriefLayout) findViewById(R.id.kcflip);
        loading_view = (LoadingView) findViewById(R.id.loading_view);

        Log.d("debug!!", tutorial.get_id());
        Log.d("debug!!", tutorial.get_title());
        Log.d("debug!!", tutorial.get_desc());
        Log.d("debug!!", tutorial.get_icon_url());

//        get_and_add_layouts_to_flip();
        find_views();
        init_views();
//        bind_listener();
        get_datas();
    }

    private void datas_to_brief() {
        tv_description_brief.setText(tutorial.get_desc());
        ImageLoader.getInstance().displayImage(tutorial.get_icon_url(), iv_cover);
        adapter_brief = new KnowledgeNetPointListAdapter(this);
        adapter_brief.add_items(points);
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
                Log.d(TAG, "change to knowledge_net_fragment");
//                switch_fragment(knowledge_net_fragment, args);
            }
        });
    }

    private void datas_to_detail() {
        tv_title_detail.setText(tutorial.get_title());
        tv_description_detail.setText(tutorial.get_desc());
        add_step(step_now);
    }

    private void add_step(IStep step) {
        LinearLayout ll_step = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.step_list_item, null);
        if (step != null) {
            ((TextView) ll_step.findViewById(R.id.tv_title)).setText(step.get_title());
            if (step.get_continue_type() == IStep.ContinueType.SELECT) {
                IStep.ISelect select = step.get_select();

                TextView tv_question = new TextView(this);
                tv_question.setText(select.get_question());
                ll_step.addView(new TextView(this));

                List<IStep.ISelectOption> select_options = select.get_select_options();
                for (IStep.ISelectOption option : select_options) {
                    Button button = new Button(this);
                    button.setText(option.get_text());
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
        new KCAsyncTask<IStep>(this) {

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
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                parents = tutorial.get_parents();
                children = tutorial.get_children();
                points = tutorial.get_related_knowledge_point_list();
                step_now = tutorial.get_first_step();
                tutorial_background = ImageLoader.getInstance().loadImageSync(tutorial.get_icon_url());
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                System.out.println(tutorial.get_title());
                iv_tutorial_background.setImageBitmap(tutorial_background);
                datas_to_views();
//                datas_to_brief();
//                datas_to_detail();
                loading_view.hide();
            }
        }.execute();
    }

    private void datas_to_views() {
        tutorial_to_views();
        parents_to_views();
        children_to_views();
        points_to_views();
    }

    private void points_to_views() {
        Log.d(TAG, "points count:" + points.size());
        adapter_points = new TutorialPointListAdapter(this);
        adapter_points.add_items(points);
        lv_points.setAdapter(adapter_points);
//        ListViewUtils.setListViewHeightBasedOnChildren(lv_points);
    }

    private void tutorial_to_views() {
//        tv_author.setText(tutorial.);
        tv_title.setText(tutorial.get_title());
        tv_desc.setText(tutorial.get_desc());
        User user = tutorial.get_creator();
        if(user != null){
            tv_author.setText(user.name);
            ImageLoader.getInstance().displayImage(
                    user.get_avatar_url(),
                    ((CircleImageView) findViewById(R.id.civ_creator_avatar))
            );
        }
    }

    private void children_to_views() {
        if(children.size() >0 ) {
            adapter_children = new TutorialTutorialListAdapter(this);
            adapter_children.add_items(children);
            lv_next.setAdapter(adapter_children);
            lv_next.setVisibility(View.VISIBLE);
            tv_next_none.setVisibility(View.GONE);
            ListViewUtils.setListViewHeightBasedOnChildren(lv_next);
        }
        else{
            tv_next_none.setVisibility(View.VISIBLE);
            lv_next.setVisibility(View.GONE);
        }
    }

    private void parents_to_views() {
        if(parents.size() >0 ) {
            adapter_parents = new TutorialTutorialListAdapter(this);
            adapter_parents.add_items(parents);
            lv_previous.setAdapter(adapter_parents);
            lv_previous.setVisibility(View.VISIBLE);
            tv_previous_none.setVisibility(View.GONE);
            ListViewUtils.setListViewHeightBasedOnChildren(lv_previous);
        }
        else{
            tv_previous_none.setVisibility(View.VISIBLE);
            lv_previous.setVisibility(View.GONE);
        }
    }

//    private void get_and_add_layouts_to_flip() {
//        brief = LayoutInflater.from(this).inflate(R.layout.tutorial_brief, null);
//        detail = LayoutInflater.from(this).inflate(R.layout.tutorial_detail, null);
////        kcFlip.set_brief_view(brief);
////        kcFlip.set_detail_view(detail);
//    }

    private void find_views() {
        fatv_back = (FontAwesomeTextView) findViewById(R.id.fatv_back);
        tv_learned_percent = (TextView) findViewById(R.id.tv_learned_percent);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_next_none = (TextView) findViewById(R.id.tv_next_none);
        tv_previous_none = (TextView) findViewById(R.id.tv_previous_none);
        lv_previous = (ListView) findViewById(R.id.lv_previous);
        lv_next = (ListView) findViewById(R.id.lv_next);
        iv_tutorial_background = (ImageView) findViewById(R.id.iv_tutorial_background);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_tutorial_background.getLayoutParams();
        rl_banner = (RelativeLayout) findViewById(R.id.rl_banner);
        sll_summary = (SelectableLinearLayout) findViewById(R.id.sll_summary);
        sll_points = (SelectableLinearLayout) findViewById(R.id.sll_points);
        sll_notes = (SelectableLinearLayout) findViewById(R.id.sll_notes);
        rl_next = (RelativeLayout) findViewById(R.id.rl_next);

        lv_notes = (ListView) findViewById(R.id.lv_notes);
        lv_points = (ListView) findViewById(R.id.lv_points);
//        find_brief_views();
//        find_detail_views();
    }

    private void init_views() {
        init_slls();
        rl_banner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BaseUtils.get_screen_size().width_px / 2));
        lv_previous.setOnItemClickListener(this);
        lv_next.setOnItemClickListener(this);
        tv_learned_percent.setText("[" + (int)(100f * tutorial.get_learned_step_count() / tutorial.get_step_count()) + "%]");
        fatv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialActivity.this, LearnActivity.class);
                intent.putExtra("tutorial", tutorial);
                startActivity(intent);
            }
        });
    }

    private void init_slls() {
        sll_summary.set_res_selected(R.drawable.tutorial_tab_selected);
        sll_points.set_res_selected(R.drawable.tutorial_tab_selected);
        sll_notes.set_res_selected(R.drawable.tutorial_tab_selected);
        sll_summary.select();
        sll_summary.setOnClickListener(this);
        sll_points.setOnClickListener(this);
        sll_notes.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ITutorial tutorial = (ITutorial) parent.getItemAtPosition(position);

        Intent intent = new Intent(TutorialActivity.this, TutorialActivity.class);
        intent.putExtra("tutorial", tutorial);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sll_points:
                if(!sll_points.isSelected()) {
                    sll_summary.unselect();
                    sll_notes.unselect();
                    sll_points.select();

                    findViewById(R.id.sv_summary).setVisibility(View.GONE);
                    lv_notes.setVisibility(View.GONE);
                    lv_points.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sll_notes:
                if(!sll_notes.isSelected()) {
                    sll_summary.unselect();
                    sll_points.unselect();
                    sll_notes.select();

                    findViewById(R.id.sv_summary).setVisibility(View.GONE);
                    lv_points.setVisibility(View.GONE);
                    lv_notes.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sll_summary:
                if(!sll_points.isSelected()) {
                    sll_notes.unselect();
                    sll_points.unselect();
                    sll_summary.select();

                    lv_notes.setVisibility(View.GONE);
                    lv_points.setVisibility(View.GONE);
                    findViewById(R.id.sv_summary).setVisibility(View.VISIBLE);
                }
                break;
        }
    }
//
//    private void find_detail_views() {
//        tv_title_detail = (TextView) detail.findViewById(R.id.tv_title_detail);
//        tv_description_detail = (TextView) detail.findViewById(R.id.tv_description_detail);
//        btn_next_step = (Button) detail.findViewById(R.id.btn_next_step);
//        ll_steps = (LinearLayout) detail.findViewById(R.id.ll_steps);
//        loading_view_detail = (LoadingView) detail.findViewById(R.id.loading_view_detail);
//    }
//
//    private void find_brief_views() {
//        lv_list_brief = (ListView) brief.findViewById(R.id.lv_list_brief);
//        tv_description_brief = (TextView) brief.findViewById(R.id.tv_description_brief);
//        iv_cover = (ImageView) brief.findViewById(R.id.iv_cover);
//    }


}
