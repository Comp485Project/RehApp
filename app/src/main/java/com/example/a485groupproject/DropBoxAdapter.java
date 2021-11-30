package com.example.a485groupproject;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class DropBoxAdapter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public DropBoxAdapter(Context context) {
        String[] privacyOptions = getResources().getStringArray(R.array.postOptions);
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, privacyOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
