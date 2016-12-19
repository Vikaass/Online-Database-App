package com.example.yash.onlinedatabaseapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Addinfo extends Activity {

    EditText Name,Email,Mobile;
    String name,email,mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_layout);

        Name = (EditText)findViewById(R.id.et_name);
        Mobile = (EditText)findViewById(R.id.et_mob);
        Email = (EditText)findViewById(R.id.et_email);

    }

    public void saveInfo (View view)
    {
        name = Name.getText().toString();
        email = Email.getText().toString();
        mobile = Mobile.getText().toString();

        //To perform the database operations we need a new thread using async-task
        //they take long time to complete

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(name, email , mobile);
        finish();
    }

    class BackgroundTask extends AsyncTask <String, Void , String>
    {
        String add_info_url;

        @Override
        protected void onPreExecute() {
            add_info_url = "https://heritable-pit.000webhostapp.com/add_info.php";

        }

        @Override
        protected String doInBackground(String... args)
        {
            String name,email,mobile;
            name = args[0];
            email = args[1];
            mobile = args[2];

            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") +"&" +
                        URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") +"&" +
                        URLEncoder.encode("mobile","UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //Don't think this is required
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                //
                httpURLConnection.disconnect();
                return "Contact Added";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String  result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}
