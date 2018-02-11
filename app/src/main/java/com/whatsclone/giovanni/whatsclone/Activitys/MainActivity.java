package com.whatsclone.giovanni.whatsclone.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whatsclone.giovanni.whatsclone.R;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference BancoFireBase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BancoFireBase.child("pontos").setValue(100);
    }
}
