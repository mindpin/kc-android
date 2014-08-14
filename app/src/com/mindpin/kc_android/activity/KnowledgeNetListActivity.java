package com.mindpin.kc_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.adapter.KnowledgeNetListAdapter;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;


public class KnowledgeNetListActivity extends KnowledgeBaseActivity {
    private List<IKnowledgeNet> net_list;
    private LoadingView loading_view;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_list_activity);

        Log.i("启动 ", "true");

        loading_view = (LoadingView) findViewById(R.id.loading_view);
        get_datas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"登出");
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                sign_out();
                break;
            case 1:
                exit_app();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void get_datas() {

        new KCAsyncTask<Void>(this){

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
        ListView listview = (ListView) this.findViewById(R.id.knowledge_net_list);

        KnowledgeNetListAdapter adapter =
                new KnowledgeNetListAdapter(this);
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

                Intent intent = new Intent(KnowledgeNetListActivity.this, KnowledgeNetActivity.class);
                intent.putExtra("knowledge_net_id", knowledge_net_id);
                startActivity(intent);
            }
        });
    }

    private void sign_out() {
        AuthenticatorsController auth = new AuthenticatorsController(this);
        auth.sign_out((User)auth.current_user());
        this.finish();
        startActivity(new Intent(this,SignInActivity.class));
    }

    private void exit_app() {
        this.finish();
    }
}