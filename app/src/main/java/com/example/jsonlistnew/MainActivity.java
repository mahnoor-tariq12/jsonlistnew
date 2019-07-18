package com.example.jsonlistnew;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<DataItem> lstData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsync myasync=new MyAsync();
        myasync.execute();


    }
    public class MyAsync extends AsyncTask<String,String,List<DataItem>>{
        @Override
        protected List<DataItem> doInBackground(String... strings) {
            ConnectionHandler connectionHandler=new ConnectionHandler();
            String response=connectionHandler.getResponse("https://api.myjson.com/bins/194lxo",1000);
            try{
                lstData = new ArrayList<>();
                JSONObject jsonObject=new JSONObject(response);
                JSONArray responsearray=jsonObject.getJSONArray("DataItem");
                for(int i=0;i<responsearray.length();i++) {
                    JSONObject arrayobjects = responsearray.getJSONObject(i);
                    lstData.add(new DataItem(R.drawable.img1,arrayobjects.getString("subjectname"),arrayobjects.getString("profname")));
                }
                return lstData;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<DataItem> s) {
            super.onPostExecute(s);
            ListView lview=(ListView)findViewById(R.id.list);
            CustomAdapter adapter=new CustomAdapter(getApplicationContext(),R.layout.itemroww,lstData);
            lview.setAdapter(adapter);
        }
    }
}
