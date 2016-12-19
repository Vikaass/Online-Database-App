package com.example.yash.onlinedatabaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Jsonparser extends Activity {

    String json_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparser);
    }

    public void getJSON(View view)
    {
        new BackgroundTask1().execute();
    }

    public void parseJSON(View view)
    {
        //we have the json text on variable result
        if(json_string == null)
        {
            Toast.makeText(getApplicationContext(), "First get json", Toast.LENGTH_LONG).show();

        }
        else
        {
            Intent intent = new Intent(this, DisplayListView.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);
        }
    }

    //This will be run separately from the main thread

    class BackgroundTask1 extends AsyncTask<Void,Void,String>
    {
        String json_url; //url where the php script gives out the json data
        String JSON_STRING;

        @Override
        protected void onPreExecute()
        {
            json_url = "http://heritable-pit.000webhostapp.com/json_get_data.php";

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                //Because here we need to take the data from the server
                //InputStream and Buffered Reader   
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();

                while((JSON_STRING = bufferedReader.readLine()) != null )
                {
                    //String builder works by appending things to itself
                     stringBuilder.append(JSON_STRING + "\n" );

                }
                //Done in inverse way in which they were initialized
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //trim the whitespaces and send it to post-execute method
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //I think in place of a string a null value gets returned when some exception occurs
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void   onPostExecute(String result ) {
            TextView textView = (TextView)findViewById(R.id.json_text_view);
            textView.setText(result);
            json_string = result;

        }
    }
}
