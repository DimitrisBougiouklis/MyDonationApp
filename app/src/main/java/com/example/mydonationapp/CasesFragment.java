package com.example.mydonationapp;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;



public class CasesFragment extends Fragment {

    CasesAdapter casesAdapter;

    public CasesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        casesAdapter = new CasesAdapter(getContext(),new ArrayList<Case>());
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchCases();
    }

    private void fetchCases(){
        FetchCasesTask fetchCasesTask = new FetchCasesTask(casesAdapter);
        fetchCasesTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);





        final ListView casesListView = (ListView)rootView.findViewById(R.id.listview_cases);

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