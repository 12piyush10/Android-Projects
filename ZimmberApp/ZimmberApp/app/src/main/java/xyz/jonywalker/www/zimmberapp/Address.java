package xyz.jonywalker.www.zimmberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Address extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button Continue;
    String flat,buildingname,landmark,phoneno,location,ServiceName,Mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        Bundle bundle = getIntent().getExtras();
        ServiceName = bundle.getString("sn");
        Mobile=bundle.getString("mn");
        ed1=(EditText) findViewById(R.id.flatno);
        ed2=(EditText) findViewById(R.id.buildingname);
        ed3=(EditText) findViewById(R.id.landmark);
        ed4=(EditText) findViewById(R.id.phoneno);
        ed5=(EditText) findViewById(R.id.location);
        Continue=(Button) findViewById(R.id.cntbtn);



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flat = ed1.getText().toString().trim();
                buildingname = ed2.getText().toString().trim();
                landmark = ed3.getText().toString().trim();
                phoneno = ed4.getText().toString().trim();
                location = ed5.getText().toString().trim();
                Intent intent=new Intent(getApplicationContext(),Booking.class);
               String ss=flat+","+buildingname+"\n"+landmark+"\n"+phoneno+"\n"+location;

                intent.putExtra("send",ss);


                intent.putExtra("s1",ServiceName);
                intent.putExtra("mobile",Mobile);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
