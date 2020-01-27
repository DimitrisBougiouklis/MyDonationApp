
package com.example.mydonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {





    @Override
    protected void onDestroy() {
        super.onDestroy();

        db.close();
    }


    static ArrayList<Case> cases;
    static ArrayList<Case> savedCases = new ArrayList<>();
    static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new CasesFragment()).commit();
        }


        db = getBaseContext().openOrCreateDatabase(
                "Account-db.db",
                Context.MODE_PRIVATE,
                null);


        db.execSQL("CREATE TABLE IF NOT EXISTS \"Account\" (\n" +
                "\t\"id\"\tINTEGER ,\n" +
                "\t\"UserName\"\tTEXT ,\n" +
                "\t\"Email\"\tTEXT ,\n" +
                "\tPRIMARY KEY(\"id\")\n" +
                ");");



        db.execSQL("CREATE TABLE IF NOT EXISTS \"saveCases\" (\n" +
                "\t\"CaseId\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"CaseId\")\n" +
                ");");





       Button btn = (Button)findViewById(R.id.preferencesBtn);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       startActivity(new Intent(MainActivity.this, GeneralDetailsActivity.class));
                                   }
                               }


        );










    }
}
