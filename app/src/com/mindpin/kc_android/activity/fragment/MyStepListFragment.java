package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.TopicTutorialListActivity;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.StepListAdapter;
import com.mindpin.kc_android.adapter.TopicListAdapter;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;




public class MyStepListFragment extends KnowledgeBaseFragment {

    private Activity activity;
    private View current_view;

    private String step_id;
    ListView listview;
    private LoadingView loading_view;

    private List<IStep> step_list;




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.my_step_list_fragment, null);
        return current_view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();

        listview = (ListView) current_view.findViewById(R.id.my_step_list);
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
                step_list = DataProvider.get_my_step_list();
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
        listview = (ListView) current_view.findViewById(R.id.my_step_list);

        StepListAdapter adapter =
                new StepListAdapter(activity);
        adapter.add_items(step_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");


                IStep step =
                        (IStep) parent.getItemAtPosition(position);

                step_id = step.get_id();
                Log.i("step_id ID ", step_id);

            }
        });
    }
}