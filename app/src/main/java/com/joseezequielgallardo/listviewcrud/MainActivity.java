package com.joseezequielgallardo.listviewcrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> items = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button newButton = this.findViewById(R.id.new_btn);
        final EditText itemEditText = this.findViewById(R.id.item_edit_text);
        final ListView listView = this.findViewById(R.id.list_view);
        adapter = new Adapter(items, MainActivity.this);
        listView.setAdapter(adapter);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = itemEditText.getText().toString();
                if(!item.isEmpty()){
                    items.add(item);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }
}
