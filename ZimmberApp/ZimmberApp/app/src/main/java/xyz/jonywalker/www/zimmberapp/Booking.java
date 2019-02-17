package xyz.jonywalker.www.zimmberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Booking extends AppCompatActivity {

    ImageButton backpage;
    TextView servicename,mno,Fulladdrs;
    EditText date,issue,code;

    Button reg,newaddress;

     String key_Date="date";
     String key_mobile="mobile";
     String key_address="address";

      String key_Issue="issue";

      String key_refercode="refer";

    String key_PNAME="pname";
    String key_PNumber="pnumber";
    String key_Orderid="orderid";

    String key_Action="action";

    String key_Servicename="sname";

    String send_url="https://www.androiddoor.com/pavan/service_register.php";



    String ServiceName,Mobile,FullAddress,P_Name,P_Number,Order_Id,Action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        Bundle bundle = getIntent().getExtras();
        ServiceName = bundle.getString("s1");
        Mobile=bundle.getString("mobile");
        P_Name="Dinesh Pratap";
        P_Number="9982669294";
        Random r=new Random();
        int n = r.nextInt(1000) + 1;

        Order_Id=String.valueOf(n);
        Action="ACCEPT";
        FullAddress=bundle.getString("send");
        mno = (TextView) findViewById(R.id.mobileno);
        //Fulladdrs=(TextView) findViewById(R.id.fulladdress);
        mno.setText(Mobile);

        //Fulladdrs.setText(FullAddress);
        servicename = (TextView) findViewById(R.id.servicename);
        servicename.setText(ServiceName);
        backpage=(ImageButton) findViewById(R.id.leftarrowbtn);


        reg=(Button) findViewById(R.id.reg);
        newaddress=(Button)findViewById(R.id.addnew);
        newaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Address.class);
                intent.putExtra("sn",ServiceName);
                intent.putExtra("mn",Mobile);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();

            }
        });

    }
    public void send() {
        date = (EditText) findViewById(R.id.enterdate);



        issue = (EditText) findViewById(R.id.issues);
        code = (EditText) findViewById(R.id.enterpromo);
        try {
            StringRequest rs = new StringRequest(Request.Method.POST, send_url, new Response.Listener<String>() {
                @Override

                public void onResponse(String response) {

                    Toast.makeText(Booking.this, "Booking Sucessfully", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Booking.this, "Please Fill Write Information", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hs = new HashMap<>();

                    hs.put(key_Date, date.getText().toString());


                    hs.put(key_address,FullAddress);
                    hs.put(key_mobile,Mobile);
                    hs.put(key_Issue, issue.getText().toString());
                    hs.put(key_refercode, code.getText().toString());


                    hs.put(key_PNAME,P_Name);
                    hs.put(key_PNumber,P_Number);
                    hs.put(key_Orderid,Order_Id);
                    hs.put(key_Action,Action);
                    hs.put(key_Servicename,ServiceName);

                    return hs;
                }
            };
            RequestQueue rqt = Volley.newRequestQueue(getApplicationContext());
            rqt.add(rs);
        } catch (Exception ex) {

            Toast.makeText(Booking.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}