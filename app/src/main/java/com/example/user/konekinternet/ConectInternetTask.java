package com.example.user.konekinternet;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by USER on 14/10/2017.
 */

public class ConectInternetTask extends AsyncTask<String,Void,String> {

    Context ctx;

    public ConectInternetTask(Context ct){
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;

        try {
            URL myURL = new URL(s1);
            HttpURLConnection myConn = (HttpURLConnection) myURL.openConnection();
            myConn.setReadTimeout(10000);
            myConn.setConnectTimeout(20000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            in = myConn.getInputStream();

            BufferedReader myBuf = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String line="";

            while ((line = myBuf.readLine()) != null){
                st.append(line+" \n");
            }

            myBuf.close();
            in.close();

            return st.toString();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        MainActivity.myText.setText(s);
    }
}
