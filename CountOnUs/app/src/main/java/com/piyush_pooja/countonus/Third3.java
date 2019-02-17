package com.piyush_pooja.countonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Third3 extends AppCompatActivity {


    public void Next4(View view){

        Intent intent = new Intent(Third3.this,Fourth4.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third3);
    }
}
