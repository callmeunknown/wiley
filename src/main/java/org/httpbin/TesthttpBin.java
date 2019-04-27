package org.httpbin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TesthttpBin {
    public static void main(String[] args) {
        String url = "https://httpbin.org";

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if( HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                System.out.println(sb.toString());
            } else {
                System.out.println("fail" + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection !=null) {
                connection.disconnect();
            }
        }
    }
}
