package xyz.jonywalker.www.zimmberapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
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

public class Refer_Earn extends AppCompatActivity {

    String mobile;
    private ProgressDialog loading;
    TextView Refer_code;
    Button share;

    String refer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer__earn);
        Bundle bundle = getIntent().getExtras();
        mobile = bundle.getString("s2");
        Refer_code=(TextView) findViewById(R.id.refercode);

        share=(Button) findViewById(R.id.share);

        getData();
    }
    private void getData() {
        String id = mobile.toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Referconfi.DATA_URL+mobile.toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Refer_Earn.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Referconfi.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            refer = collegeData.getString(Referconfi.KEY_Refer);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Refer_code.setText(refer);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);


                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Use My Referral code and get Rs 500 Free on signup using code"+refer+"https://www.Zimmber.com/invite/"+refer);

               // Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
               // whatsappIntent.setType("text/plain");
               // whatsappIntent.setPackage("com.whatsapp");

               // whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Use My Referral code and get Rs 500 Free on signup using code"+refer+"https://www.Zimmber.com/invite/"+refer);
                try {
                  //  startActivity(whatsappIntent);
                    startActivity(Intent.createChooser(intent, "Share"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Refer_Earn.this, "Whatsup not found", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
