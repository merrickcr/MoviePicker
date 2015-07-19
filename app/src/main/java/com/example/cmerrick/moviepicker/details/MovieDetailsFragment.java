package com.example.cmerrick.moviepicker.details;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cmerrick.moviepicker.Movie;
import com.example.cmerrick.moviepicker.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    Movie movie;

    TextView title;
    TextView summary;
    TextView voteAverage;
    TextView releaseDate;

    ImageView backdrop;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO For Instructor:
        // What is the best way to store keys to be used between activities (and even modules)
        // R.strings? Static Dedicated Objects?

        movie = (Movie) getActivity().getIntent().getSerializableExtra("movie");

        title = (TextView) view.findViewById(R.id.title);
        summary = (TextView) view.findViewById(R.id.summary);
        voteAverage = (TextView) view.findViewById(R.id.vote_average);
        backdrop = (ImageView) view.findViewById(R.id.backdrop);
        releaseDate = (TextView) view.findViewById(R.id.release_date);

        title.setText(movie.getTitle());
        summary.setText(movie.getOverview());
        voteAverage.setText(getResources().getString(R.string.average_rating) + movie.getVoteAverage());
        releaseDate.setText(movie.getReleaseDate());

        Picasso.with(getActivity()).load(movie.getFullBackDropPath()).into(backdrop);

    }
}
