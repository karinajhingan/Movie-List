package model;


import java.util.ArrayList;
import java.util.List;


public class MovieList {
    private final List<Movie> movieList;

    //EFFECTS: constructs an empty ArrayList that holds Movies
    public MovieList() {
        this.movieList = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds Movie to List, allows duplicates
    void addMovieToList(Movie m) {
        this.movieList.add(m);

    }

    //MODIFIES: this
    //EFFECTS: adds movies where category = s, to new list
    public List<Movie> filterCategory(String s) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getCategory() == s) {
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
    }


    //MODIFIES: this
    //EFFECTS: adds movies where rating = r, to new list
    public List<Movie> filterRating(int r) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getRating() == r) {
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
    }

    //MODIFIES: this
    //EFFECTS: adds movies where name = s, to new list
    public List<Movie> findMovie(String s) {
        List<Movie> foundMovies = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getName() == s) {
                foundMovies.add(m);
            }
        }
        return foundMovies;
    }

    //MODIFIES: this
    //EFFECTS: adds movies where rating = 0, to new list
    public List<Movie> getListOfUnwatched() {
        List<Movie> unwatched = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getRating() == 0) {
                unwatched.add(m);
            }
        }
        return unwatched;
    }

    //getter
    public List<Movie> getMovieList() {
        return this.movieList;
    }
}
