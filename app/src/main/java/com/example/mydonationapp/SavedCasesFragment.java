package com.example.mydonationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class SavedCasesFragment extends Fragment {


    CasesAdapter casesAdapter;
    public SavedCasesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!MainActivity.savedCases.isEmpty()){
        casesAdapter = new CasesAdapter(getContext(), MainActivity.savedCases);}

    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.general_details_fragment, container, false);


        ListView casesListView = (ListView) rootView.findViewById(R.id.listview_savedCases);

        casesListView.setAdapter(casesAdapter);
        casesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position , long id){
                Intent intent = new Intent(getActivity(), FullCaseDescription.class);

                intent.putExtra("intVariableName", position);

                startActivity(intent);

            }
        });


        return rootView;
    }
}