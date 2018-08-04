package com.joseezequielgallardo.listviewcrud;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpdateDialogFragment extends DialogFragment {

    public UpdateDialogFragment(){}

    public static UpdateDialogFragment newInstance (String item, int position){
        UpdateDialogFragment fragment = new UpdateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("item", item);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface OnUpdateListener{
        public void onUpdate(String item, int position);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            onUpdateListener = (OnUpdateListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnUpdateListener");
        }
    }

    OnUpdateListener onUpdateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        return inflater.inflate(R.layout.update_dialog_fragment, parent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        getDialog().setTitle(R.string.title_dialog_fragment);

        final EditText updateItemEditText = view.findViewById(R.id.update_edit_text);
        final Button updateButton = view.findViewById(R.id.update_button);

        final String itemFetched = getArguments().getString("item");
        final int itemPositionFetched = getArguments().getInt("position");

        Log.v("FETCHED", itemFetched);
        Log.v("FETCHED", Integer.toString(itemPositionFetched));

        updateItemEditText.setText(itemFetched);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedItem = updateItemEditText.getText().toString();
                onUpdateListener.onUpdate(updatedItem, itemPositionFetched);
                dismiss();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        //Issue: Linear layout's width or height set to MATCH_PARENT does not work
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
