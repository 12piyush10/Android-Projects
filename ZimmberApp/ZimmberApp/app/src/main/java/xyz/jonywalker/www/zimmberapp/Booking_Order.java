package xyz.jonywalker.www.zimmberapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Booking_Order extends AppCompatActivity {

    String mobilenum;
    TextView userid,pname,pnumber,sname,p_action,address;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__order);
        Bundle bundle = getIntent().getExtras();
        mobilenum = bundle.getString("s2");

        button=(Button) findViewById(R.id.ordernow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Display.class);
                startActivity(intent);
            }
        });
        userid=(TextView) findViewById(R.id.orderid);
        pname=(TextView) findViewById(R.id.providername);
        pnumber=(TextView) findViewById(R.id.providernumber);
        sname=(TextView) findViewById(R.id.services);
        p_action=(TextView) findViewById(R.id.action);
        address=(TextView) findViewById(R.id.address);
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();


        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Current Order");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Current Order");

        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Complete Order");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Complete Order");
        host.addTab(spec);
        for(int i=0;i<host.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(16);

        }
        getData();

    }
    private void getData() {

        String id = mobilenum.toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
       // loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = OrderConfi.DATA_URL+mobilenum.toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Booking_Order.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String name="";
        String phone="";
        String email = "";
        String city = "";
        String action = "";
        String useraddress = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(OrderConfi.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            name = collegeData.getString(OrderConfi.KEY_Order);
            phone = collegeData.getString(OrderConfi.KEY_Service);
            email = collegeData.getString(OrderConfi.KEY_Phone);
            city = collegeData.getString(OrderConfi.KEY_Date);
            action = collegeData.getString(OrderConfi.KEY_Issue);
            useraddress = collegeData.getString(OrderConfi.KEY_Address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userid.setText(name);
        pname.setText(phone);
        pnumber.setText(email);
        sname.setText(city);
        p_action.setText(action);
        address.setText(useraddress);

    }


}
