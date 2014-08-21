package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.TopicListAdapter;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;


public class TopicListFragment extends KnowledgeBaseFragment {

    private Activity activity;
    private View current_view;

    private String topic_id;
    private TextView topic_title;
    private TextView topic_tutorial_count;
    GridView listview;
    private LoadingView loading_view;

    private List<ITopic> topic_list;




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.topic_list_activity, null);
        return current_view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        this.topic_id = args.getString("topic_id");

        topic_title = (TextView) current_view.findViewById(R.id.topic_title);
        topic_tutorial_count = (TextView) current_view.findViewById(R.id.topic_tutorial_count);

        listview = (GridView) current_view.findViewById(R.id.topic_list);
        loading_view = (LoadingView) current_view.findViewById(R.id.loading_view);

        get_datas();
    }

    private void get_datas() {

        new KCAsyncTask<Void>(activity){

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                topic_list = DataProvider.get_topic_list();
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
        listview = (GridView) current_view.findViewById(R.id.topic_list);

        TopicListAdapter adapter =
                new TopicListAdapter(activity);
        adapter.add_items(topic_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");

                ITopic topic =
                        (ITopic) parent.getItemAtPosition(position);

                topic_id = topic.get_id();
                Log.i("topic_id ID ", topic_id);

                Bundle args = new Bundle();
                args.putString("topic_id", topic_id);
            }
        });
    }
}
