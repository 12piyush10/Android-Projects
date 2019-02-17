package com.piyush_pooja.countonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {


    public static final String ADMOB_ID_BANNER = "ca-app-pub-3940256099942544/6300978111";




    public void begin(View view){

        Intent intent = new Intent(MainActivity.this,First1.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

  /*  private void setUpLayoutAdmob() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relout);
        boolean b = true;
            if (b) {
                adView = new AdView(this);
                adView.setAdUnitId(ADMOB_ID_BANNER);
                adView.setAdSize(AdSize.SMART_BANNER);

                layout.addView(adView);
                AdRequest mAdRequest = new AdRequest.Builder().build();
                adView.loadAd(mAdRequest);


            } else {
                layout.setVisibility(View.GONE);
            }

    }*/

}
