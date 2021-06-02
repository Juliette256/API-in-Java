package com.example.usingapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String myUrl = "https://picsum.photos/v2/list";
    ListView listView;
    ProgressDialog progressDialog;
    Button displayData;
    UserAdapter adapter;
    ArrayList<User>items=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayData=findViewById(R.id.button);
        listView=findViewById(R.id.recyclerView);


        displayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();
            }
        });
    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("processing results");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            try { URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(myUrl);
                    //open a URL connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        result += (char) data;
                        data = isw.read(); }

                    // return the data to onPostExecute method
                    return result;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return result;        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();


            try {

                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject;

                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);


                    String id = jsonObject.getString("id");
                    String title = jsonObject.getString("author");
                    String body = jsonObject.getString("height");
                    String img = jsonObject.getString("download_url");

                    User user = new User();
                    user.setId(Integer.parseInt(id));
                    user.setTitle(title);
                    user.setBody(body);
                    user.setUri(img);

                    items.add(user);
                }
                adapter = new UserAdapter(MainActivity.this, items);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();

            }


        }}}