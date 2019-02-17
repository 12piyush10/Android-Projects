package xyz.jonywalker.www.zimmberapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
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

public class Display extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   ViewPager mViewPager;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    TextView tv;

    String profileuser;

    private String name;
    private String phone;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle bundle = getIntent().getExtras();
        profileuser = bundle.getString("s1");

        getData();






        b1=(Button) findViewById(R.id.handyman);
        b2=(Button) findViewById(R.id.cleaning);
        b3=(Button) findViewById(R.id.salon);
        b4=(Button) findViewById(R.id.plumbing);
        b5=(Button) findViewById(R.id.fridge);
        b6=(Button) findViewById(R.id.washingmachine);
        b7=(Button) findViewById(R.id.electrical);
        b8=(Button) findViewById(R.id.Airconditioner);
        b9=(Button) findViewById(R.id.painting);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Carpentry";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                intent.putExtra("mobile",profileuser);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Cleaning";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                intent.putExtra("mobile",profileuser);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Saloon";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Plumbing";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Refrigerator";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Washing";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Electrical";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Airconditioner";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="Painting";
                Intent intent=new Intent(getApplicationContext(),HandymanService.class);
                intent.putExtra("service",s1);
                startActivity(intent);
            }
        });

        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.user);
        nav_user.setText(profileuser);

    }
    private void getData() {
        String id = profileuser.toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }


        String url = Config.DATA_URL+profileuser.toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Display.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            name = collegeData.getString(Config.KEY_FNAME);
            phone = collegeData.getString(Config.KEY_EMAIL);
            email = collegeData.getString(Config.KEY_EMAIL);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent=new Intent(getApplicationContext(),Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_location) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {


            Intent intent=new Intent(getApplicationContext(),Profile.class);
            intent.putExtra("s2",profileuser);
            startActivity(intent);

        } else if (id == R.id.nav_order) {
            Intent intent=new Intent(getApplicationContext(),Booking_Order.class);
            intent.putExtra("s2",profileuser);
            startActivity(intent);

        } else if (id == R.id.nav_wallet) {

            Intent intent=new Intent(getApplicationContext(),Wallet.class);
            intent.putExtra("s2",profileuser);
            startActivity(intent);
        } else if (id == R.id.refer_earn) {
            Intent intent=new Intent(getApplicationContext(),Refer_Earn.class);
            intent.putExtra("s2",profileuser);
            startActivity(intent);

        } else if (id == R.id.offer) {

            Intent intent=new Intent(getApplicationContext(),Offers.class);
            intent.putExtra("s2",profileuser);
            startActivity(intent);
        } else if(id == R.id.notification){
            Intent intent=new Intent(getApplicationContext(),Notification.class);
            intent.putExtra("s2",profileuser);
            startActivity(intent);

        } else if(id == R.id.nav_about){

        } else if(id == R.id.nav_call){

            Intent intent=new Intent(getApplicationContext(),Contect.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
