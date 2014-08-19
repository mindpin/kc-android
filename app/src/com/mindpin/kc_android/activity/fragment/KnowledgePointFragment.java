package com.mindpin.kc_android.activity.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.KnowledgeNetTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;

public class KnowledgePointFragment  extends KnowledgeBaseFragment {
    private Activity activity;
    private View current_view;

    TextView knowledge_point_name;
    TextView knowledge_point_desc;
    TextView knowledge_net_name;

    ListView listview;
    private String knowledge_point_id;
    private LoadingView loading_view;
    private IKnowledgePoint point;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.knowledge_point_activity, null);
        return current_view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        this.knowledge_point_id = args.getString("knowledge_point_id");

        knowledge_point_name = (TextView) current_view.findViewById(R.id.knowledge_point_name);
        knowledge_point_desc = (TextView) current_view.findViewById(R.id.knowledge_point_desc);
        knowledge_net_name = (TextView) current_view.findViewById(R.id.knowledge_net_name);

        listview = (ListView) current_view.findViewById(R.id.knowledge_point_tutorial_list);
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
                point = DataProvider.get_knowledge_point(knowledge_point_id);
                point.get_knowledge_net();
                point.get_tutorial_list();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                loading_view.hide();
            }
        }.execute();
    }


    private void build_view(){
        knowledge_point_name.setText(point.get_name());
        knowledge_point_desc.setText(point.get_desc());


        final IKnowledgeNet net = point.get_knowledge_net();
        knowledge_net_name.setText(net.get_name());
        knowledge_net_name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                TODO change to fragment
//                Intent intent = new Intent(KnowledgePointActivity.this, KnowledgeNetActivity.class);
//                intent.putExtra("knowledge_net_id", net.get_id());
//                startActivity(intent);
            }
        });


        List<ITutorial> list = point.get_tutorial_list();
        KnowledgeNetTutorialListAdapter adapter = new KnowledgeNetTutorialListAdapter(activity);
        adapter.add_items(list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");

                ITutorial tutorial = (ITutorial)parent.getItemAtPosition(position);

                String tutorial_id = tutorial.get_id();

                Log.i("教程ID ", tutorial_id);

//                Intent intent = new Intent(KnowledgePointFragment.this, TutorialActivity.class);
//                intent.putExtra("tutorial_id", tutorial_id);
//                startActivity(intent);

            }
        });
    }
}
