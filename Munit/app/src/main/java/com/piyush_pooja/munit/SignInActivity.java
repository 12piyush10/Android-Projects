package com.piyush_pooja.munit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SignInActivity extends AppCompatActivity {

    private Toolbar mSigninToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mSigninToolbar = (Toolbar) findViewById(R.id.signinToolbar);
        setSupportActionBar(mSigninToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
