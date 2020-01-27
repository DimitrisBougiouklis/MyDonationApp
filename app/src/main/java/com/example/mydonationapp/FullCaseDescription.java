package com.example.mydonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class FullCaseDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_case_description);

        Intent intent = getIntent();

        int position = intent.getIntExtra("intVariableName", 0);
        Log.d("teo", "" + position);

        TextView nameView = findViewById(R.id.fullNameTxt);
        TextView descrView = findViewById(R.id.fullDescriptionTxt);
        TextView expirationView = findViewById(R.id.expirationDateTxt);
        TextView amountView = findViewById(R.id.amountNeededTxt);
        TextView ibanView = findViewById(R.id.iBan);

        final Case thisCase = MainActivity.cases.get(position);







        nameView.setText("Case's Full Name: "+thisCase.getName());
        descrView.setText("Description: \n"+thisCase.getFullDescription());
        expirationView.setText("Donate till: "+thisCase.getExpireDate());
        amountView.setText("Needed Amount: "+thisCase.getMoneyAmount());
        ibanView.setText("Case's Iban: \n"+thisCase.getIban());

        Button saveCaseBtn = findViewById(R.id.saveCaseBtn);
        saveCaseBtn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {


                                       MainActivity.savedCases.add(thisCase);
                                   }
                               }


        );






    }
}
