package xyz.jonywalker.www.zimmberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class User_Signup extends AppCompatActivity  {


    private AwesomeValidation awesomeValidation;
    EditText name, mobile, email, password, refer,city;
    Button b1, b2, b3;
   // CheckBox checkBox;
    String key_first = "name";
    String key_last = "mobile";
    String key_email = "email";
    String key_pass = "password";
    String key_city = "city";
    String key_refre = "refer";
    String key_myrefer = "myrefer";

    String send_url = "https://www.androiddoor.com/homeservice/register.php";
    final Random r = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__signup);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //checkBox = (CheckBox) findViewById(R.id.checkBox);
       // b2 = (Button) findViewById(R.id.button);
        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.mobile, "^[2-9]{2}[0-9]{8}$", R.string.nameerror);
        //awesomeValidation.addValidation(this, R.id.editTextDob, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.nameerror);
       // awesomeValidation.addValidation(this, R.id.editTextAge, Range.closed(13, 60), R.string.ageerror);

        b3 = (Button) findViewById(R.id.button2);
        refer = (EditText) findViewById(R.id.refer);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    send();
                    Intent in = new Intent(getApplication(), Login.class);
                    startActivity(in);
                }



            }
        });
      //  b2.setOnClickListener(new View.OnClickListener() {
        //    @Override
    //        public void onClick(View v) {
     //           Intent in = new Intent(getApplication(), Provider_Signup.class);
       //         startActivity(in);
     //       }
      //  });

    }


    public void send() {
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        city = (EditText) findViewById(R.id.city);


//
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //testing if it is checked or unchecked
//                if (isChecked) {
//
//                    refer.setVisibility(View.VISIBLE);
//
//                } else {
//                    refer.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
        {


            try {
                StringRequest rs = new StringRequest(Request.Method.POST, send_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(User_Signup.this, "insert", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(User_Signup.this, "error aa gayi", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hs = new HashMap<>();
                        hs.put(key_first, name.getText().toString());
                        hs.put(key_last, mobile.getText().toString());
                        hs.put(key_email, email.getText().toString());
                        hs.put(key_pass, password.getText().toString());
                        hs.put(key_city, city.getText().toString());
                        hs.put(key_myrefer, refer.getText().toString());

                        int n = r.nextInt(100000) + 1;
                        String refer = String.valueOf(n);
                        hs.put(key_refre, refer);


                        return hs;

                    }
                };
                RequestQueue rqt = Volley.newRequestQueue(getApplicationContext());
                rqt.add(rs);

            } catch (Exception ex) {
                Toast.makeText(User_Signup.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}