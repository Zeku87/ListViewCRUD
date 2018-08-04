package com.joseezequielgallardo.listviewcrud;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UpdateDialogFragment.OnUpdateListener {

    public ArrayList<String> items = new ArrayList<>();
    private Adapter adapter;

    LinearLayout deleteButtonsLayout, addItemLayout;
    Button acceptDeletionButton, cancelDeletionButton;

    Button addItemButton;
    EditText itemEditText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Delete buttons layout*/
        deleteButtonsLayout = this.findViewById(R.id.delete_buttons_layout);
        acceptDeletionButton = this.findViewById(R.id.accept_delete_button);
        cancelDeletionButton = this.findViewById(R.id.cancel_delete_button);

        /* Ordinary layout */
        addItemLayout = this.findViewById(R.id.add_item_layout);
        addItemButton = this.findViewById(R.id.add_item_button);
        itemEditText = this.findViewById(R.id.item_edit_text);
        listView = this.findViewById(R.id.list_view);

        adapter = new Adapter(items, MainActivity.this);
        listView.setAdapter(adapter);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemEditText.getText().toString();
                if(!item.isEmpty()){
                    String emptyString = "";
                    itemEditText.setText(emptyString);
                    items.add(item);
                    adapter.notifyDataSetChanged();

                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateFragmentDialog(i);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
                enableDeletionView();
                return true;
            }
        });

        acceptDeletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO eliminate items selected
            }
        });

        cancelDeletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableDeletionView();
            }
        });
    }

    private void updateFragmentDialog(int i){
        FragmentManager manager = getSupportFragmentManager();
        UpdateDialogFragment dialogFragment = UpdateDialogFragment.newInstance(
                items.get(i), i
        );

        dialogFragment.show(manager, getString(R.string.title_dialog_fragment));
    }

    private void enableDeletionView(){
        deleteButtonsLayout.setVisibility(View.VISIBLE);
        addItemLayout.setVisibility(View.GONE);
        adapter.setDeletable(true);
        adapter.notifyDataSetChanged();
    }

    private void disableDeletionView(){
        deleteButtonsLayout.setVisibility(View.GONE);
        addItemLayout.setVisibility(View.VISIBLE);
        adapter.setDeletable(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdate(String item, int position){
        items.set(position, item);
        adapter.notifyDataSetChanged();
    }
}
