package com.example.user15.intermediatesecondapplication.activities;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;

import com.example.user15.intermediatesecondapplication.R;
import com.example.user15.intermediatesecondapplication.adpater.CustomListAdapter;
import com.example.user15.intermediatesecondapplication.model.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomListActivity extends AppCompatActivity {

    private RecyclerView recyclerCustomListView;
    private List<Flower> flowerList;
    private CustomListAdapter customListAdapter;
    private LruCache<String, Bitmap> cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        recyclerCustomListView = findViewById(R.id.recyclerCustomListView);
        flowerList = new ArrayList<>();

        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        int cacheSize1 = (am.getMemoryClass() * 1024 * 1024) / 8;

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;

        cache = new LruCache<String, Bitmap>(cacheSize1) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };

        recyclerCustomListView.setLayoutManager(new LinearLayoutManager(this));
        new FetchFlowerData().execute();
        customListAdapter = new CustomListAdapter(flowerList, this);
        recyclerCustomListView.setAdapter(customListAdapter);
    }

    class FetchFlowerData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            String flowerResult;

            try {
                URL url = new URL("http://services.hanselandpetal.com/feeds/flowers.json");

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }


                flowerResult = buffer.toString();
                getJsonObject(flowerResult);
                return flowerResult;
            } catch (IOException e) {
                Log.e("error", String.valueOf(e));
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }
    }

    class FetchFlowerPicture extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;
            BufferedInputStream bufferedInputStream = null;
            Bitmap photo;

            try {
                URL url = new URL("http://services.hanselandpetal.com/photos/" + strings[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                photo = BitmapFactory.decodeStream(bufferedInputStream);
                return photo;
            } catch (IOException e) {
                Log.e("error", String.valueOf(e));
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }
    }

    private void getJsonObject(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject;
            Bitmap photo;

            for (int i = 0; i < 1; i++) {
                jsonObject = (JSONObject) jsonArray.get(i);

                Flower flower = new Flower();
                flower.setName(jsonObject.getString("name"));
                String photoPath = jsonObject.getString("photo");

                if (getImageFromCache(photoPath) == null) {
                    photo = new FetchFlowerPicture().doInBackground(photoPath);
                    addImageToCache(photoPath, photo);
                } else {
                    photo = getImageFromCache(photoPath);
                }
                flower.setBitmap(photo);
                flowerList.add(flower);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addImageToCache(String photoPath, Bitmap photo) {
        cache.put(photoPath, getResizedBitmap(photo, 100));
    }

    private Bitmap getImageFromCache(String photoPath) {
        return cache.get(photoPath);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
