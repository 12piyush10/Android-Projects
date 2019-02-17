package xyz.jonywalker.www.zimmberapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Allservices extends AppCompatActivity {


    TabHost tabHost;
    private ListView listView;
    ImageButton back;
    TextView servicename;
    Button booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_service);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("service");
        servicename = (TextView) findViewById(R.id.servicename);
        servicename.setText(name);

        booking=(Button) findViewById(R.id.booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Booking.class);
                startActivity(intent);
            }
        });
        back=(ImageButton) findViewById(R.id.leftarrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Display.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        sendRequest();

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();


        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("About");
        spec.setContent(R.id.tab1);
        spec.setIndicator("About");

        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Rate Card");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Rate Card");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("FAQ");
        spec.setContent(R.id.tab3);
        spec.setIndicator("FAQ");
        host.addTab(spec);

        for(int i=0;i<host.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(16);

        }

    }
    private void sendRequest(){


        String s1="carpenter";
        final String JSON_URL = "https://www.androiddoor.com/pavan/servicerate.php";
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Allservices.this,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        RateList cl = new RateList(this,ParseJSON.names,ParseJSON.longitudes);
        listView.setAdapter(cl);
    }

}

