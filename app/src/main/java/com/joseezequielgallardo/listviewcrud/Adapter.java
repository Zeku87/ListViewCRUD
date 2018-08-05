package com.joseezequielgallardo.listviewcrud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.joseezequielgallardo.listviewcrud.data.Item;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private ArrayList<Item> items;
    private Context context;

    //Determine wether the list is in delete mode or not
    private boolean isDeletableMode = false;

    private CheckBox checkBox;
    private TextView itemTextView;

    public Adapter(ArrayList<Item> items, Context context){
        this.items = items;
        this.context = context;
    }


    @Override
    public int getCount() {
        int size = 0;
        if(this.getItems() != null)
            size = this.getItems().size();
        return size;
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_view, null);
        }

        itemTextView = view.findViewById(R.id.item_text_view);
        checkBox = view.findViewById(R.id.item_select_check_box);
        itemTextView.setText(getItems().get(position).getText());

        enableDeletionMode(position);
        disableDeletionMode();

        checkBox.setChecked(this.getItems().get(position).isChecked());

        return view;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setDeletableMode(boolean deletableMode){
        this.isDeletableMode = deletableMode;
    }

    public boolean isDeletableMode(){
        return this.isDeletableMode;
    }

    public boolean isPositionChecked(int position){
        return getItems().get(position).isChecked();
    }

    public void enableDeletionMode(int position){
        if(isDeletableMode()){
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setText(getItems().get(position).getText());

            itemTextView.setVisibility(View.GONE);
        }
    }

    public void disableDeletionMode(){
        if(!isDeletableMode()){
            checkBox.setVisibility(View.GONE);
            itemTextView.setVisibility(View.VISIBLE);
        }
    }
}
