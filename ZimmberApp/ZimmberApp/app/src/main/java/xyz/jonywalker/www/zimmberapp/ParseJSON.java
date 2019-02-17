package xyz.jonywalker.www.zimmberapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dell on 23-05-2017.
 */
public class ParseJSON {

    public static String[] names;

    public static String[] longitudes;

    public static final String JSON_ARRAY = "result";

    public static final String KEY_NAME = "service";

    public static final String KEY_LON = "price";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);


            names = new String[users.length()];

            longitudes = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                Log.d("pawan","Value Not Fetch");

                names[i] = jo.getString(KEY_NAME);

                longitudes[i]=jo.getString(KEY_LON);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
