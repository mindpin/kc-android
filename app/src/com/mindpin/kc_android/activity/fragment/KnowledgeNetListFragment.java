package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.KnowledgeNetListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;


/**
 * Created by Administrator on 2014/8/19.
 */
public class KnowledgeNetListFragment extends KnowledgeBaseFragment {
    private List<IKnowledgeNet> net_list;
    private LoadingView loading_view;
    private View current_view;
    private Activity activity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.knowledge_net_list_activity, null);
        return current_view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loading_view = (LoadingView) current_view.findViewById(R.id.loading_view);
        get_datas();

        // 打开测试 topic fragment 页面
        Button btn_menu_topic = (Button) current_view.findViewById(R.id.btn_menu_topic);
        btn_menu_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("kc", "btn_menu_topic click");
                switch_fragment(new TopicListFragment());
            }
        });
    }

    private void get_datas() {

        new KCAsyncTask<Void>(activity){

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                net_list = DataProvider.get_knowledge_net_list();
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
        ListView listview = (ListView) current_view.findViewById(R.id.knowledge_net_list);

        KnowledgeNetListAdapter adapter =
                new KnowledgeNetListAdapter(activity);
        adapter.add_items(net_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");

                IKnowledgeNet knowledge_net =
                        (IKnowledgeNet) parent.getItemAtPosition(position);

                String knowledge_net_id = knowledge_net.get_id();
                Log.i("knowledge_net_id ID ", knowledge_net_id);

                Bundle args = new Bundle();
                args.putString("knowledge_net_id", knowledge_net_id);
                KnowledgeNetFragment knowledge_net_fragment = new KnowledgeNetFragment();
                switch_fragment(knowledge_net_fragment, args);
            }
        });
    }



}
