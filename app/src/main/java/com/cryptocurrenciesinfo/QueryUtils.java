package com.cryptocurrenciesinfo;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private QueryUtils() {}

    public static List<CryptoCurr> fetchData(String reqUrl) {
        URL url = createUrl(reqUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch(IOException e) {
            e.printStackTrace();
        }
        List<CryptoCurr> list = extractFeatureFromJson(jsonResponse);
        return list;
    }

    private static URL createUrl(String reqUrl) {
        URL url = null;
        try {
            url = new URL(reqUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.i("Response code", String.valueOf(urlConnection.getResponseCode()));
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private static List<CryptoCurr> extractFeatureFromJson(String jsonResponse) {
        if(TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<CryptoCurr> list = new ArrayList<>();

        try {

            JSONArray cryptoArray = new JSONArray(jsonResponse);

            for(int i = 0; i <cryptoArray.length(); i++) {
                JSONObject currentCrypto = cryptoArray.getJSONObject(i);
                String name = currentCrypto.getString("name");
                String symbol = currentCrypto.getString("symbol");
                int _id = currentCrypto.getInt("rank");
                double price = currentCrypto.getDouble("price_usd");
                long volume = currentCrypto.getLong("24h_volume_usd");
                long mcu = currentCrypto.getLong("market_cap_usd");
                double avSupply = currentCrypto.getDouble("available_supply");
                double totSupply = currentCrypto.getDouble("total_supply");
                double pc1 = currentCrypto.getDouble("percent_change_1h");
                double pc24 = currentCrypto.getDouble("percent_change_24h");
                double pcw = currentCrypto.getDouble("percent_change_7d");

                CryptoCurr cryptoCurr = new CryptoCurr(_id, name, symbol, price, mcu, avSupply, totSupply, volume, pc1, pc24, pcw);
                list.add(cryptoCurr);
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
        return list;
    }




}
