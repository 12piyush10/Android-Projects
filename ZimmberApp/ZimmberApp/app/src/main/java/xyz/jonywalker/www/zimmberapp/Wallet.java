package xyz.jonywalker.www.zimmberapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Wallet extends AppCompatActivity {

    String mobile;
    private ProgressDialog loading;
    TextView User_Wallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Bundle bundle = getIntent().getExtras();
        mobile = bundle.getString("s2");
        User_Wallet=(TextView) findViewById(R.id.user_wallet);
        getData();
    }
    private void getData() {
        String id = mobile.toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Walletconfi.DATA_URL+mobile.toString().trim();

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
                        Toast.makeText(Wallet.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String refer="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Walletconfi.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            refer = collegeData.getString(Walletconfi.KEY_Wallet);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        User_Wallet.setText(refer);


    }

}

