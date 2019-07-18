package com.example.jsonlistnew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionHandler {
    static String response;

    public ConnectionHandler() {
    }
    public static String getResponse(String url,int timeout)
    {
        HttpURLConnection connection=null;
        try{
            URL urll=new URL(url);
            connection= (HttpURLConnection) urll.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(timeout);
            int statuscode=connection.getResponseCode();
            if(statuscode==200 || statuscode==201)
            {
                InputStream inputStream=connection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader br=new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder=new StringBuilder();
                String data="";
                while((data=br.readLine())!=null)
                {
                    stringBuilder=stringBuilder.append(data+"\n");
                }
                response =stringBuilder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
                return response;
    }
}
