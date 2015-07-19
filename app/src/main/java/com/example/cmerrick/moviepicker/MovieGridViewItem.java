package com.example.cmerrick.moviepicker;

import android.graphics.drawable.Drawable;

public class MovieGridViewItem {

    Drawable backdrop;

    String title;

    MovieGridViewItem(Drawable backdrop, String title){
        this.backdrop = backdrop;
        this.title = title;
    }

    public Drawable getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(Drawable backdrop) {
        this.backdrop = backdrop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
