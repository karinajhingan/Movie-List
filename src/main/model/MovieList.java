package model;

import java.util.HashSet;
import java.util.Set;


public class MovieList {
    private Set<Movie> movieList;

    //EFFECTS: constructs an empty HashSet that holds Movies
    public MovieList() {
        Set<Movie> movieList = new HashSet<>();

    }

    //MODIFIES: this
    void addMovieToList(Movie m) {
        movieList.add(m);

    }

    //MODIFIES: this
    public Set filterCategory(String s) {
        for (Movie m : movieList) {
            if (m.getCategory() != s) {
                movieList.remove(m);
            }
        }
        return movieList;
    }


    //MODIFIES: this
    public Set filterRating(int r) {
        for (Movie m : movieList) {
            if (m.getRating() != r) {
                movieList.remove(m);
            }
        }
        return movieList;
    }

    //MODIFIES: this
    public Movie findMovie(String s) {
        for (Movie m : movieList) {
            if (m.getName() == s) {
                return m;
            }
        }
        return null;
    }

    //MODIFIES: this
    public Set listOfUnwatched() {
        for (Movie m : movieList) {
            if (m.getRating() != 0) {
                movieList.remove(m);
            }
        }
        return movieList;
    }

    //getter
    public Set getMovieList() {
        return movieList;
    }
}
