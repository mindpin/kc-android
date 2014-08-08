package com.mindpin.kc_android.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;

import java.util.List;



public class KnowledgeNetListAdapter extends ArrayAdapter<IKnowledgeNet>{
    private final Context context;
    private final List<IKnowledgeNet> values;

    public KnowledgeNetListAdapter(Context context,
                                   int resource_id,
                                   List<IKnowledgeNet> values) {
        super(context, resource_id, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.knowledge_net_list_item, parent, false);
        TextView name_view = (TextView) rowView.findViewById(R.id.knowledge_net_list_name);
        TextView point_count_view =
                (TextView) rowView.findViewById(R.id.knowledge_net_list_point_count);

        name_view.setText(values.get(position).get_name());
        point_count_view.setText(Integer.toString(values.get(position).get_point_count()));


        return rowView;
    }
}