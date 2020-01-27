package com.example.mydonationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CasesAdapter extends ArrayAdapter<Case> {
    //static ArrayList<Case> cases;
    private Context context;

    public CasesAdapter(Context context, ArrayList<Case> objects) {
        super(context, 0, objects);
        MainActivity.cases = objects;

        //this.cases = objects;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;



       // Case aCase = cases.get(position);
        Case aCase = MainActivity.cases.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.list_item_cases, null);


        TextView nameView = rowView.findViewById(R.id.nameTxt);
        TextView descrView = rowView.findViewById(R.id.descriptionTxt);
        TextView expirationView = rowView.findViewById(R.id.expirationTxt);

        nameView.setText(aCase.getName());
        descrView.setText(aCase.getBriefDescription());
        expirationView.setText(aCase.getExpireDate());

        return rowView;
    }


}
