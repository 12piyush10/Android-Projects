package com.piyush_pooja.countonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tenth10 extends AppCompatActivity {

    public void NextFin(View view){

        Intent intent = new Intent(Tenth10.this,FinalAct.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth10);
    }
}
