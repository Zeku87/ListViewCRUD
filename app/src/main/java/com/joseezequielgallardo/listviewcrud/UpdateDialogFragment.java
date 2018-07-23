package com.joseezequielgallardo.listviewcrud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class UpdateDialogFragment extends DialogFragment {

    public UpdateDialogFragment(){}

    public static UpdateDialogFragment newInstance (String item){
        UpdateDialogFragment fragment = new UpdateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("item", item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        return inflater.inflate(R.layout.update_dialog_fragment, parent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        getDialog().setTitle(R.string.title_dialog_fragment);

        final EditText updateItemEditText = view.findViewById(R.id.update_edit_text);
        String itemFetched = getArguments().getString("item");

        updateItemEditText.setText(itemFetched);
    }

}
