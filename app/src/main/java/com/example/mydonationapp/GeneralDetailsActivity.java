package com.example.mydonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class GeneralDetailsActivity extends AppCompatActivity {

    private static final String API_KEY = "AIzaSyAdDzGyXfK2_t80oa0zEv1oGe0cZPB0c_s";

    private static final int PICK_FROM_GALLERY = 1;




    private CasesAdapter casesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_details);


        TextView testTxt = findViewById(R.id.TestText);




        Button nameChangeBtn = findViewById(R.id.detailsChangeButton);
        Button mapsBtn = findViewById(R.id.mapsBtn);
        nameChangeBtn.setOnClickListener(BtnListener);
        mapsBtn.setOnClickListener(mapBtnListener);

        Button cameraBtn = findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(CameraBtnListener);

        Cursor cursorEdit = MainActivity.db.rawQuery("select * from Account ",
                null);
        Cursor cursorPlain = MainActivity.db.rawQuery("select * from Account",
                null);
        Cursor cursor = MainActivity.db.rawQuery("select * from Photo",
                null);
        while(cursor.moveToNext()){
            ImageView image = findViewById(R.id.image_View);
            Bitmap bitmap = BitmapFactory.decodeByteArray(cursor.getBlob(1), 0, cursor.getBlob(1).length);
            image.setImageBitmap(bitmap);}


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

    private View.OnClickListener mapBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(GeneralDetailsActivity.this, MapsActivity.class));


        }
    };

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

    private View.OnClickListener CameraBtnListener;

    {
        CameraBtnListener = new View.OnClickListener() {
            @Override
            public void onClick (View v){
                try {
                    if (ActivityCompat.checkSelfPermission(GeneralDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(GeneralDetailsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        ImageView image = findViewById(R.id.image_View);
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (reqCode){
                case PICK_FROM_GALLERY:
                    Uri selectedImage = data.getData();
                    Log.i("TAG", "Some exception "+selectedImage);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        image.setImageBitmap(bitmap);

                        byte[] bitmapAsBytes =getBitmapAsByteArray(bitmap);

                        MainActivity.db.execSQL("INSERT OR REPLACE INTO Photo (id,Photo) VALUES (1,'"+bitmapAsBytes+"');");

                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }

        }else {
            //Toast.makeText(PostImage.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }



    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


}
