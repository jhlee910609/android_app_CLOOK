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

    public static String getData(String url) throws IOException {

        // TODO ======================== 그냥 스트링보다 퍼포먼스 향상에 도움이 된당 ㅋㅋㅋㅋ
        StringBuilder result = new StringBuilder();
        URL serverUrl = new URL(url);

        HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
        con.setRequestMethod("GET");
        // 1. 분기한다...
        if (url.contains("skplanetx")) {
            con.setRequestProperty("appKey", "64df5102-cf08-332f-b749-0d30ca8e4c07");
            con.setRequestProperty("Accept", "application/json");
        }
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                result.append(temp);
            }
        } else {
            Log.e("NETWORK", "Error_code" + responseCode);
        }
        return result.toString();
    }

    public static void newTask(final String url, final TaskInterface taskInterface) {
        Log.e("Remote", "==== newTask");
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                Log.e("Remote", "==========================doInBackground");
                Log.e("Remote", "==== params[0] : " + params[0]);
                String result = "";
                try {
                    result = getData(params[0]);
                    Log.i("REMOTE ; NETWORK ===== url : ", result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                Log.e("Remote", "================= onPostExecute");
                taskInterface.exectue(result, url);
            }
        }.execute(url);
    }
}


