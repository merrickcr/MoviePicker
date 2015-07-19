package com.example.cmerrick.moviepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MovieAdapter class. uses view holder pattern as seen from below
 *
 * http://www.perfectapk.com/android-gridview-viewholder.html
 */
public class MovieAdapter extends BaseAdapter{

    private Context context;

    private List<Movie> movies;

    final static String BACKDROP_URL = "http://image.tmdb.org/t/p/w185/";

    public MovieAdapter(Context context, ArrayList<Movie> items) {
        super();

        this.context = context;
        movies = items;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MovieViewHolder movieViewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.movie_grid_view_item, parent, false);

            // initialize the view holder
            movieViewHolder = new MovieViewHolder();
            movieViewHolder.title = (TextView) convertView.findViewById(R.id.title);
            movieViewHolder.backdrop = (ImageView) convertView.findViewById(R.id.backdrop);
            convertView.setTag(movieViewHolder);
        } else {
            // recycle the already inflated view
            movieViewHolder = (MovieViewHolder) convertView.getTag();
        }

        Movie item = getItem(position);
        String backdropUrl = BACKDROP_URL + item.getBackdropPath();

        item.setFullBackDropPath(backdropUrl);

        Picasso.with(convertView.getContext()).load(backdropUrl).into(movieViewHolder.backdrop);
        movieViewHolder.title.setText(item.getTitle());

        //TODO for instructor
        //how to just load a bitmap to save in a field using picasso, without creating a new AsyncTask. (using default Picasso)
        //i want to save the bitmap into Movie. and then pass movie to the next activity so I don't have to laod it again.

        return convertView;

    }

    public static class MovieViewHolder{
        ImageView backdrop;
        TextView title;
    }

    public List<Movie> getObjects(){
        return movies;
    }

}
