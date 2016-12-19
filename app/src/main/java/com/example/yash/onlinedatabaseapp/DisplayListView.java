package com.example.yash.onlinedatabaseapp;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_list_view);
        listView = (ListView) findViewById(R.id.list_view);
        //The contructor of contactsAdapter class takes in context and resource.
        contactAdapter = new ContactAdapter(this, R.layout.row_layout);
        listView.setAdapter(contactAdapter);
     //   startActivity(new Intent(this, MainActivity.class));


        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d("wifiInfo", wifiInfo.toString());
        Log.d("SSID",wifiInfo.getSSID());

        json_string = getIntent().getExtras().getString("json_data");

        try {
          //  jsonObject   = new JSONObject(json_string);
            jsonArray  = new JSONObject(json_string).getJSONArray("server_response");
            int count = 0;
            String name,email,mobile;
            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                email = JO.getString("email");
                mobile = JO.getString("mobile");

                //Creating an object of contacts class with the given information
                Contacts contacts = new Contacts(name,email, mobile);
                contactAdapter.add(contacts);
                count++;

               // Toast.makeText(getApplicationContext(), json_string,Toast.LENGTH_LONG).show();


            }
        } catch (JSONException e) {

        }


    }
}
