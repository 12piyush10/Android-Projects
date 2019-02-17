package com.piyush_pooja.countonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Eight8 extends AppCompatActivity {

    public void Next9(View view){

        Intent intent = new Intent(Eight8.this,Ninth9.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight8);
    }
}
