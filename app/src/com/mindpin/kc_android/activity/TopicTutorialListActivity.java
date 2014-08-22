package com.mindpin.kc_android.activity;

import android.os.Bundle;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.adapter.TopicTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.network.HttpApi;
import com.mindpin.kc_android.utils.KCAsyncTask;

import it.sephiroth.android.library.widget.HListView;

/**
 * Created by fushang318 on 2014/8/22.
 */
public class TopicTutorialListActivity extends KnowledgeBaseActivity{
    private LoadingView loading_view;
    private HListView topic_tutorial_list_view;
    private ITopic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_tutorial_list);

//        topic = (ITopic)getIntent().getSerializableExtra("topic");
        loading_view = (LoadingView) findViewById(R.id.loading_view);
        topic_tutorial_list_view = (HListView) findViewById(R.id.topic_tutorial_list);

        get_datas();
    }

    public void get_datas() {
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                topic = HttpApi.get_topic_list().get(1);
                topic.get_tutorial_list();
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
        TopicTutorialListAdapter adapter = new TopicTutorialListAdapter(this);
        adapter.add_items(topic.get_tutorial_list());
        topic_tutorial_list_view.setAdapter(adapter);
    }
}
