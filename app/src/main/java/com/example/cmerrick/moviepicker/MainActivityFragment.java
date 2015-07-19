package com.example.cmerrick.moviepicker;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.cmerrick.moviepicker.details.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MovieRequestHelper.DiscoverMovieListener {

    public MovieRequestHelper movieRequestHelper;

    ArrayList<Movie> movies = new ArrayList<Movie>();

    MovieAdapter adapter = null;

    GridView gridView;

    final static String BACKDROP_URL = "http://image.tmdb.org/t/p/w500/";

    public MainActivityFragment() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_sort_popularity){
            Comparator<Movie> popularityComparator = new Comparator<Movie>(){
                @Override
                public int compare(Movie lhs, Movie rhs) {
                    return lhs.getPopularity().compareTo(rhs.getPopularity());
                }
            };

            //TODO for instructor
            //Can this logic be simplified? to reset the adapter and view to sorted order
            Collections.sort(adapter.getObjects(), popularityComparator);
            adapter = new MovieAdapter(getActivity(), (ArrayList) adapter.getObjects());
            //without adding the gridView.setAdapter() elements did not change..
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            return false;
        }
        else if(item.getItemId() == R.id.action_sort_vote_average){

            Comparator<Movie> voteComparator = new Comparator<Movie>(){
                @Override
                public int compare(Movie lhs, Movie rhs) {
                    return lhs.getVoteAverage().compareTo(rhs.getVoteAverage());
                }
            };

            //TODO for instructor
            //Can this logic be simplified? to reset the adapter and view to sorted order
            Collections.sort(adapter.getObjects(), voteComparator);
            adapter = new MovieAdapter(getActivity(), (ArrayList) adapter.getObjects());
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            return false;
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        movieRequestHelper = new MovieRequestHelper(this);

        movieRequestHelper.sendDiscoverMovieRequest();

        gridView = (GridView) view.findViewById(R.id.gridView);

    }

    public void setupMovieList() {

        if (movies != null) {
            //setup adapter/gridview
            adapter = new MovieAdapter(getActivity(), movies);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //get clicked movie,
                    Movie movie = (Movie)gridView.getAdapter().getItem(position);

                    Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                    intent.putExtra("movie", movie);

                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void setupDiscoverMovieResults(String result) {

        Log.e("MAIN", "result = " + result);

        try {
            JSONObject jsonResult = new JSONObject(result);

            JSONArray entries = jsonResult.optJSONArray("results");

            for (int i = 0; i < entries.length(); i++) {
                JSONObject jsonMovie = entries.optJSONObject(i);
                Movie movie = new Movie(jsonMovie);

                movies.add(movie);
            }

            setupMovieList();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


}
