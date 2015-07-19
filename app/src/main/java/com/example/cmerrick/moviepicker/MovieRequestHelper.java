package com.example.cmerrick.moviepicker;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * includes functions to load Movie Data from TMDB
 */
public class MovieRequestHelper {

    private static final String DISCOVER_BASE_URL = "http://api.themoviedb.org/3/discover/movie";

    private static final String API_KEY = "9c91836287681308ff38e33f8cad7208";

    private static final String size = "";

    DiscoverMovieListener movieListener;

    public MovieRequestHelper(DiscoverMovieListener listener) {
        try {
            movieListener = listener;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendDiscoverMovieRequest() {
        DiscoverMovieTask task = new DiscoverMovieTask();
        task.execute();
    }

    public void getPopularMovies() {

    }

    public void getMoviePoster() {

    }

    public class DiscoverMovieTask extends AsyncTask<Void, Void, String> {

        public DiscoverMovieTask() {
            super();
        }


        @Override
        protected String doInBackground(Void... params) {

            BufferedReader reader = null;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            String returnString = null;

            try {

                Uri uri = Uri.parse(DISCOVER_BASE_URL).buildUpon()
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("sort_by", "popularity.desc")
                    .build();

                URL url = new URL(uri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                returnString = buffer.toString();
            } catch (Exception e) {
                Log.e("MovieRequestHelper", e.getMessage());
            } finally {
                try {

                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }

                    if (reader != null) {
                        reader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    Log.e("MovieRequestHelper", e.getMessage());
                }
            }
            return returnString;
        }

        @Override
        protected void onPostExecute(String string) {
            movieListener.setupDiscoverMovieResults(string);
        }
    }

    public interface DiscoverMovieListener {

        public void setupDiscoverMovieResults(String result);
    }

}
