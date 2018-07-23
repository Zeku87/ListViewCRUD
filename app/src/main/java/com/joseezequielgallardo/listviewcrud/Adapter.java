package com.joseezequielgallardo.listviewcrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private ArrayList<String> items;
    private Context context;

    public Adapter(ArrayList<String> items, Context context){
        this.items = items;
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_view, null);
        }

        final TextView itemTextView = view.findViewById(R.id.item_text_view);
        itemTextView.setText(this.items.get(pos));

        return view;
    }
}
