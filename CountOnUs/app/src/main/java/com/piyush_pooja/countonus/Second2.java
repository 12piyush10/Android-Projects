package com.piyush_pooja.countonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Second2 extends AppCompatActivity {

    public void Next3(View view){

        Intent intent = new Intent(Second2.this,Third3.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
    }
}
