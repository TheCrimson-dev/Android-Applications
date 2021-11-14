package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherForecast extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "WeatherForecast";

    ProgressBar progressBar;
    ImageView weather;
    TextView max_temp;
    TextView min_temp;
    TextView current_temp;
    Spinner spin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        current_temp = findViewById(R.id.currentTemperature);
        min_temp = findViewById(R.id.minTemperature);
        max_temp = findViewById(R.id.maxTemperature);
        weather = findViewById(R.id.currentWeatherType);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ForecastQuery forecast = new ForecastQuery();
        forecast.execute();
    }

    private class ForecastQuery extends AsyncTask<String, Integer, String> {
        String minTemperature;
        String maxTemperature;
        String currentTemperature;
        Bitmap weatherPicture;

        @Override
        protected String doInBackground(String... strings) {
            try {
                Bundle bundle = getIntent().getExtras();
                String cityChoose = bundle.getString("city");
                URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityChoose + ",ca&APPID=8a9f6bdf05f4ce482ae83bcd2785b80f&mode=xml&units=metric");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000 /* milliseconds */);
                connection.setConnectTimeout(15000 /* milliseconds */);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                InputStream in = connection.getInputStream();
                try {
                    XmlPullParser parsed = Xml.newPullParser();
                    parsed.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parsed.setInput(in, null);

                    while (parsed.getEventType() != XmlPullParser.END_DOCUMENT) {
                        if (parsed.getEventType() == XmlPullParser.START_TAG) {
                            if (parsed.getName().equals("temperature")) {
                                currentTemperature = parsed.getAttributeValue(null, "value");
                                publishProgress(25);
                                minTemperature = parsed.getAttributeValue(null, "min");
                                publishProgress(50);
                                maxTemperature = parsed.getAttributeValue(null, "max");
                                publishProgress(75);

                            } else if (parsed.getName().equals("weather")) {
                                String icon = parsed.getAttributeValue(null, "icon");
                                String fileN = icon + ".png";

                                Log.i(ACTIVITY_NAME, "Looking for file: " + fileN);

                                if (fileExistance((fileN))) {
                                    FileInputStream readFile = null;

                                    try {
                                        readFile = openFileInput(fileN);

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }

                                    Log.i(ACTIVITY_NAME, "Found the file locally");
                                    weatherPicture = BitmapFactory.decodeStream(readFile);
                                } else {
                                    String iconLink = "https://openweathermap.org/img/w/" + fileN;
                                    weatherPicture = getImage(new URL(iconLink));
                                    FileOutputStream optStream = openFileOutput(fileN, Context.MODE_PRIVATE);

                                    weatherPicture.compress(Bitmap.CompressFormat.PNG, 80, optStream);
                                    optStream.flush();
                                    optStream.close();
                                }
                                publishProgress(100);


                            }


                        }
                        parsed.next();
                    }
                } finally {
                    in.close();
                }
            } catch (Exception n) {
                n.printStackTrace();
            }

            return null;
        }

        public boolean fileExistance(String fname) {
            File newFile = getBaseContext().getFileStreamPath(fname);
            return newFile.exists();
        }

        public Bitmap getImage(URL url) {
            HttpsURLConnection connect = null;
            try {
                connect = (HttpsURLConnection) url.openConnection();
                connect.connect();
                int connectResponse = connect.getResponseCode();

                if (connectResponse == 200) {
                    return BitmapFactory.decodeStream(connect.getInputStream());

                } else return null;

            } catch (Exception e) {
                return null;
            } finally {
                if (connect != null) {
                    connect.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String i) {
            progressBar.setVisibility(View.INVISIBLE);
            weather.setImageBitmap(weatherPicture);
            current_temp.setText(currentTemperature + "C\u00b0");
            min_temp.setText(minTemperature + "C\u00b0");
            max_temp.setText(maxTemperature + "C\u00b0");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }


    }
    @Override

    public void onBackPressed() {

        super.onBackPressed();

        setResult(Activity.RESULT_CANCELED);

        finish();

    }


}