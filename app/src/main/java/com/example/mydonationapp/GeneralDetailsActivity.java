package com.example.mydonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GeneralDetailsActivity extends AppCompatActivity {







    //static ArrayList<Case> savedCases;
    private CasesAdapter casesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_general_details, new SavedCasesFragment()).commit();
        }





        TextView testTxt = findViewById(R.id.TestText);

        Button nameChangeBtn = findViewById(R.id.detailsChangeButton);
        nameChangeBtn.setOnClickListener(BtnListener);

        Cursor cursorEdit = MainActivity.db.rawQuery("select * from Account",
                null);
        Cursor cursorPlain = MainActivity.db.rawQuery("select * from Account",
                null);

        while (cursorEdit.moveToNext()){
            if (cursorEdit.getString(1) != "Change Your Username" && cursorEdit.getString(2) != "Change Your E-mail"){

                EditText userNameTxt = findViewById(R.id.userNameTxt);
                EditText emailTxt = findViewById(R.id.emailTxt);
                userNameTxt.setText(cursorEdit.getString(1));
                emailTxt.setText(cursorEdit.getString(2));
             }
            else break;
        }

        while(cursorPlain.moveToNext()){

            testTxt.setText("Your Username is: "+cursorPlain.getString(1)+" \n" +
                    "Your E-mail is: "+cursorPlain.getString(2));}





    }

    private View.OnClickListener BtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText userNameTxt = findViewById(R.id.userNameTxt);
            EditText emailTxt = findViewById(R.id.emailTxt);

            String email = emailTxt.getText().toString();
            String name = userNameTxt.getText().toString();



            Log.d("TEO", name);
            Log.d("TEO" , email);


            MainActivity.db.execSQL("INSERT OR REPLACE INTO Account (id,UserName,Email) VALUES (1,'"+name+"','"+email+"');");


            TextView testTxt = findViewById(R.id.TestText);
            Cursor cursor = MainActivity.db.rawQuery("select * from Account",
                    null);
            while(cursor.moveToNext()){

                testTxt.setText("Your username is:"+cursor.getString(1)+" \n" +
                        "Your email is: "+cursor.getString(2));}










        }



    };







}




