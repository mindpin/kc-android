package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.KnowledgeNetTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;


/**
 * Created by fushang318 on 2014/8/19.
 */
public class KnowledgeNetFragment extends KnowledgeBaseFragment implements View.OnClickListener{
    private Activity activity;
    private View current_view;
    private String knowledge_net_id;
    private TextView tv_title;
    private TextView tv_description;
    private ListView lv_tutorials;
    private ListView lv_points;
    private ToggleButton btn_tutorials;
    private ToggleButton btn_points;
    private LoadingView loading_view;
    private IKnowledgeNet net;
    private List<ITutorial> tutorials;
    private List<IKnowledgePoint> points;
    private KnowledgeNetTutorialListAdapter adapter_tutorials;
    private KnowledgeNetPointListAdapter adapter_points;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.knowledge_net, null);
        return current_view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        knowledge_net_id = args.getString("knowledge_net_id");

        tv_title = (TextView) current_view.findViewById(R.id.tv_title);
        tv_description = (TextView) current_view.findViewById(R.id.tv_description);
        lv_tutorials = (ListView) current_view.findViewById(R.id.lv_list_tutorials);
        lv_points = (ListView) current_view.findViewById(R.id.lv_list_points);
        btn_tutorials = (ToggleButton) current_view.findViewById(R.id.btn_tutorials);
        btn_points = (ToggleButton) current_view.findViewById(R.id.btn_points);
        btn_tutorials.setOnClickListener(this);
        btn_points.setOnClickListener(this);
        loading_view = (LoadingView) current_view.findViewById(R.id.loading_view);

        get_datas();
    }

    private void get_datas() {
        new KCAsyncTask<Void>(activity) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                net = DataProvider.get_knowledge_net(knowledge_net_id);
                tutorials = net.get_tutorial_list();
                points = net.get_knowledge_point_list();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                loading_view.hide();
            }
        }.execute();
    }

    private void build_view() {
        tv_title.setText(net.get_name());
        tv_description.setText(net.get_desc());

        bind_tutorials();
        bind_points();
    }

    private void bind_tutorials() {
        adapter_tutorials = new KnowledgeNetTutorialListAdapter(activity);
        adapter_tutorials.add_items(tutorials);
        lv_tutorials.setAdapter(adapter_tutorials);
        lv_tutorials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                ITutorial tutorial = (ITutorial) parent.getItemAtPosition(position);
                String tutorial_id = tutorial.get_id();

                // TODO change to fragment
//                Intent intent = new Intent(KnowledgeNetActivity.this, TutorialActivity.class);
//                intent.putExtra("tutorial_id", tutorial_id);
//                startActivity(intent);
            }
        });
    }

    private void bind_points() {
        adapter_points = new KnowledgeNetPointListAdapter(activity);
        adapter_points.add_items(points);
        lv_points.setAdapter(adapter_points);
        lv_points.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                IKnowledgePoint knowledgePoint = (IKnowledgePoint) parent.getItemAtPosition(position);
                String knowledge_point_id = knowledgePoint.get_id();

                // TODO change to fragment
//                Intent intent = new Intent(KnowledgeNetActivity.this, KnowledgePointActivity.class);
//                intent.putExtra("knowledge_point_id", knowledge_point_id);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tutorials) {
            lv_tutorials.setVisibility(View.VISIBLE);
            lv_points.setVisibility(View.GONE);
            btn_tutorials.setChecked(true);
            btn_points.setChecked(false);
        } else {
            lv_tutorials.setVisibility(View.GONE);
            lv_points.setVisibility(View.VISIBLE);
            btn_tutorials.setChecked(false);
            btn_points.setChecked(true);
        }
    }
}
