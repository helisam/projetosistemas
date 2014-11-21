package br.com.embarcado.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by helisam.bentes on 19/11/2014.
 */
public class HttpHelper {
    private static final String TAG = "Http";
    private static boolean LOG_ON = false;

    public static String doGet(String url, String charset) throws IOException {
        if (LOG_ON) {
            Log.d(TAG, ">> Http.doGet: " + url);
        }
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        InputStream in = conn.getInputStream();
        String s = IOUtils.toString(in, charset);
        if (LOG_ON) {
            Log.d(TAG, "<< Http.doGet: " + url);
        }
        in.close();
        conn.disconnect();
        return s;
    }
}
