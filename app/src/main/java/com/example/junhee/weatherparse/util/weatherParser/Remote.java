package com.example.junhee.weatherparse.util.weatherParser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JunHee on 2017. 7. 9..
 */

public class Remote {

    public static class Kma {
        public static String getData(String url) throws IOException {

            String result = "";

            URL serverUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
            } else {
                Log.e("NETWORK", "Error_code" + responseCode);
            }
            return result;
        }

        public static void newTask(final KmaTaskInterfce kmaTaskInterfce) {
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {
                    String result = "";
                    try {
                        result = getData(params[0]);
                        Log.i("NETWORK", result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(String result) {
                    kmaTaskInterfce.postExecute(result);
                }
                // getUrl(); 로 바꾼다...
            }.execute(kmaTaskInterfce.getUrl());
        }
    }

    public static class SkWeather {

        public static String getData(String url) throws IOException {

            String result = "";

            URL serverUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("appKey" ,"2824ebc2-dd6d-318c-baa6-3cc11f6508b7");
            con.setRequestProperty("Accept", "application/json");


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
            } else {
                Log.e("NETWORK", "Error_code" + responseCode);
            }
            return result;
        }

        public static void newTask(final SkTaskInterface skTaskInterface) {
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... params) {
                    String result = "";
                    try {
                        result = getData(params[0]);
                        Log.i("NETWORK", result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(String result) {
                    skTaskInterface.postSkExecute(result);
                }
                // getUrl(); 로 바꾼다...
            }.execute(skTaskInterface.getSkUrl() );
        }
    }

}



