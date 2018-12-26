package com.example.user15.helloworldapplication.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user15.helloworldapplication.R;
import com.example.user15.helloworldapplication.fragments.ShowWeatherFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    private Bundle bundle;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().setTitle("Weather");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if ("HttpUrl".equals(bundle.getString("findType")))
                new FetchWeatherData().execute(bundle.getString("cityName"));
            else
                findWeatherDataThroughVolley(bundle.getString("cityName"));
        }
    }

    private void findWeatherDataThroughVolley(String city) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://openweathermap.org/data/2.5/weather?q=" + city + "&appid=b6907d289e10d714a6e88b30761fae22";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getJsonObject(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void getJsonObject(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject mainObject = (JSONObject) jsonObject.get("main");
            JSONObject windObject = (JSONObject) jsonObject.get("wind");

            String temp = mainObject.getString("temp");
            String pressure = mainObject.getString("pressure");
            String humidity = mainObject.getString("humidity");
            String temp_min = mainObject.getString("temp_min");
            String temp_max = mainObject.getString("temp_max");
            String speed = windObject.getString("speed");

            Bundle bundle = getWeatherBundle(temp, pressure, humidity, temp_max, temp_min, speed);
            getWeatherData(bundle);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bundle getWeatherBundle(String temp, String pressure, String humidity, String temp_max, String temp_min, String speed) {
        bundle = new Bundle();
        bundle.putString("temp", temp);
        bundle.putString("pressure", pressure);
        bundle.putString("humidity", humidity);
        bundle.putString("temp_min", temp_min);
        bundle.putString("temp_max", temp_max);
        bundle.putString("speed", speed);

        return bundle;
    }

    void getWeatherData(Bundle bundle) {

        ShowWeatherFragment showWeatherFragment = new ShowWeatherFragment();
        showWeatherFragment.setArguments(bundle);

        if (!isFinishing() && !isDestroyed()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.showWeatherFrame, showWeatherFragment);
            fragmentTransaction.commit();
        }
    }

    class FetchWeatherData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection httpURLConnection = null;
            BufferedReader reader = null;
            String forecastJsonStr;

            Log.d("json", "" + strings[0]);

            try {
                URL url = new URL("https://openweathermap.org/data/2.5/weather?q=" + strings[0] + "&appid=b6907d289e10d714a6e88b30761fae22");

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                forecastJsonStr = buffer.toString();

                getJsonObject(forecastJsonStr);
                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("error", String.valueOf(e));
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }
    }
}
