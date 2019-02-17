package xyz.jonywalker.www.zimmberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText ed1, ed2;
    Button login, reg;

    private String username;
    private String password;

    String key_email = "phone";
    String key_pass = "password";

    String send1_url = "https://www.androiddoor.com/homeservice/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ed1 = (EditText) findViewById(R.id.editmobile);
        ed2 = (EditText) findViewById(R.id.editpass);
        login = (Button) findViewById(R.id.button);
        reg = (Button) findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    send1();



            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getApplicationContext(), User_Signup.class);
                startActivity(it);
            }
        });
    }


    public void send1(){
        username = ed1.getText().toString().trim();
        password = ed2.getText().toString().trim();
        try{
            StringRequest rs=new StringRequest(Request.Method.POST,send1_url, new Response.Listener<String>() {
                @Override

                public void onResponse(String response) {

                    if(response.trim().equals("success")){
                        openProfile();
                    }else{
                        Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Login.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> hs=new HashMap<>();

                    hs.put(key_email,username);
                    hs.put(key_pass,password);

                    return hs;
                }
            };
            RequestQueue rqt= Volley.newRequestQueue(getApplicationContext());
            rqt.add(rs);

        }catch (Exception ex){

            Toast.makeText(Login.this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void openProfile(){
        if( ed1.getText().toString().length() > 0 && ed2.getText().toString().length() > 0 ) {
            Intent intent = new Intent(this, Display.class);
            intent.putExtra("s1", username);
            startActivity(intent);
        }else{
            Toast.makeText(Login.this, "please fill value", Toast.LENGTH_SHORT).show();
        }
    }
}



