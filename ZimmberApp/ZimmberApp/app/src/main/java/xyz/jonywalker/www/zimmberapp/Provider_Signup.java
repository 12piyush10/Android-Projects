package xyz.jonywalker.www.zimmberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class Provider_Signup extends AppCompatActivity {

    Spinner sp1, sp2;
    String[] s1 = {"city", "jaipur", "kota", "bikaner"};
    String[] s2 = {"expert", "handman", "cleaning", "saloon", "plumber", "rfefrigerator", "washing", "electronic", "ac", "painting"};
    EditText ed1, ed2, ed3, ed4;
    Button b1, b2, b3;
    String key_first = "first";
    String key_last = "last";
    String key_email = "email";
    String key_pass = "password";
    String key_city = "city";
    String key_category = "category";

    String send_url = "https://www.androiddoor.com/homeservice/providerregister.php";
    String ss1,ss2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider__signup);
        b2 = (Button) findViewById(R.id.button);
        b3 = (Button) findViewById(R.id.button3);



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();

                Intent in = new Intent(getApplication(), Login.class);
                startActivity(in);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplication(), User_Signup.class);
                startActivity(in);
            }
        });
        sp1 = (Spinner) findViewById(R.id.spinner);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, s1);
        sp1.setAdapter(adp);


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ss1 = String.valueOf(sp1.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        ArrayAdapter adp1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, s2);
        sp2.setAdapter(adp1);


        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ss2 = String.valueOf(sp2.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }

    public void send() {
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);


        try {
            StringRequest rs = new StringRequest(Request.Method.POST, send_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(Provider_Signup.this, "insert", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Provider_Signup.this, "error aa gayi", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hs = new HashMap<>();
                    hs.put(key_first, ed1.getText().toString());
                    hs.put(key_last, ed2.getText().toString());
                    hs.put(key_email, ed3.getText().toString());
                    hs.put(key_pass, ed4.getText().toString());
                    hs.put(key_city, ss1.toString());
                    hs.put(key_category, ss2.toString());


                    return hs;

                }
            };
            RequestQueue rqt = Volley.newRequestQueue(getApplicationContext());
            rqt.add(rs);

        } catch (Exception ex) {
            Toast.makeText(Provider_Signup.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




}