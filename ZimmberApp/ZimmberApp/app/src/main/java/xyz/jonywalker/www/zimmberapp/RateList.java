package xyz.jonywalker.www.zimmberapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell on 23-05-2017.
 */
public class RateList extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Activity context;

    public RateList(Activity context, String[] names, String[] desc) {
        super(context, R.layout.rate_card, names);
        this.context = context;
        this.names = names;
        this.desc = desc;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.rate_card, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.repairtype);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.rate);

        textViewName.setText(names[position]);
        textViewDesc.setText(desc[position]);

        return  listViewItem;
    }
}

