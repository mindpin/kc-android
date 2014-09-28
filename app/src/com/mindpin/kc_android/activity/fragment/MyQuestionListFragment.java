package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.LearnActivity;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.adapter.MyQuestionListAdapter;
import com.mindpin.kc_android.models.interfaces.IQuestion;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;


public class MyQuestionListFragment extends KnowledgeBaseFragment {

    private Activity activity;
    private View current_view;

    private String question_id;
    ListView listview;
    private LoadingView loading_view;

    private List<IQuestion> question_list;




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.my_question_list_fragment, null);
        return current_view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();

        listview = (ListView) current_view.findViewById(R.id.my_question_list);
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
                question_list = DataProvider.get_my_question_list();
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
        listview = (ListView) current_view.findViewById(R.id.my_question_list);

        MyQuestionListAdapter adapter =
                new MyQuestionListAdapter(activity);
        adapter.add_items(question_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");


                IQuestion question =
                        (IQuestion) parent.getItemAtPosition(position);

                question_id = question.get_id();
                Log.i("question_id ID ", question_id);

                Intent intent = new Intent(activity, LearnActivity.class);
                intent.putExtra("step_id", question.get_step_id());
                intent.putExtra("tutorial_id", question.get_tutorial_id());
                activity.startActivity(intent);

            }
        });
    }
}