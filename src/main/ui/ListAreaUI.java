package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;

public class ListAreaUI {
    private Movie movie;
    private MovieList ml;
    private JList uiList;

    public ListAreaUI() {
        uiList = new JList<>();
    }

    public JList allMovies() {
        return uiList;
    }
}

