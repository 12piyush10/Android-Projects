package com.piyush_pooja.munit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {

    private Button mSignInBtn;
    private Button mRegisterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        mSignInBtn = (Button) findViewById(R.id.GetStartedSignIn);
        mRegisterBtn = (Button) findViewById(R.id.GetStartedRegister);

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signintent = new Intent(GetStartedActivity.this,SignInActivity.class);
                startActivity(signintent);

            }
        });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent = new Intent(GetStartedActivity.this,RegisterActivity.class);
                startActivity(registerintent);

            }
        });
    }
}
