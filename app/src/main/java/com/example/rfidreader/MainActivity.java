package com.example.rfidreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private FirebaseDatabase FDatabase;
    private DatabaseReference databaseReference;

    private TextView idcard;
    private TextView uidtext;
    private TextView namatext;
    private TextView nimtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.butexit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Anda akan keluar dari aplikasi", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }
        });

        idcard = findViewById(R.id.idcard);
        uidtext = findViewById(R.id.uidtext);
        namatext = findViewById(R.id.namatext);
        nimtext = findViewById(R.id.nimtext);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FDatabase = FirebaseDatabase.getInstance();
        databaseReference = FDatabase.getReference("DATA RFID");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idcard.setText(snapshot.child("ID").getValue().toString());
                uidtext.setText(snapshot.child("UID").getValue().toString());
                namatext.setText(snapshot.child("Nama").getValue().toString());
                nimtext.setText(snapshot.child("Nim").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,
                        "Gagal Membaca", Toast.LENGTH_SHORT).show();

            }
        });


    }
}