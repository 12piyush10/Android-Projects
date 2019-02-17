package xyz.jonywalker.www.zimmberapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;

public class Notification extends AppCompatActivity {

    private ProgressDialog loading;
    TextView tv1,tv2;
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;
    String Notification,Notification_Content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();

        tv1=(TextView) findViewById(R.id.noti);
        tv2=(TextView) findViewById(R.id.noti_content);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        getData();
        sendNotification();
    }
    public void sendNotification() {


    }
    private void getData() {

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = NotificationConfi.DATA_URL;

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
                        Toast.makeText(Notification.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray peoples = jsonObject.getJSONArray(NotificationConfi.JSON_ARRAY);
            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                Notification = c.getString(NotificationConfi.KEY_Notification);
                Notification_Content = c.getString(NotificationConfi.KEY_Content);



                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(NotificationConfi.KEY_Notification, Notification);
                persons.put(NotificationConfi.KEY_Content, Notification_Content);


                personList.add(persons);
                Intent intent = new Intent(this,Notification.class);
                //Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(this)

                                .setSmallIcon(R.drawable.notification)
                                .setContentTitle(Notification)
                                .setContentText(Notification_Content);
                mBuilder.setContentIntent(pendingIntent);


// Gets an instance of the NotificationManager service//

                NotificationManager mNotificationManager =

                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//When you issue multiple notifications about the same type of event, it’s best practice for your app to try to update an existing notification with this new information, rather than immediately creating a new notification. If you want to update this notification at a later date, you need to assign it an ID. You can then use this ID whenever you issue a subsequent notification. If the previous notification is still visible, the system will update this existing notification, rather than create a new one. In this example, the notification’s ID is 001//



                mNotificationManager.notify(001, mBuilder.build());
            }

            ListAdapter adapter = new SimpleAdapter(
                    Notification.this, personList, R.layout.notification_item,
                    new String[]{NotificationConfi.KEY_Notification,NotificationConfi.KEY_Content},
                    new int[]{R.id.noti, R.id.noti_content}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
