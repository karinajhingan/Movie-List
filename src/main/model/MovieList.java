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
    //EFFECTS: adds Movie to MovieList, doesn't allow duplicates
    public void addMovieToList(Movie m) {
        if (!movieList.contains(m)) {
            this.movieList.add(m);

        }
    }

    //MODIFIES: this
    //EFFECTS: produces list of movies where category == s
    public List<Movie> filterCategory(String s) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getCategory().equals(s)) {
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
    }


    //MODIFIES: this
    //EFFECTS: produces list of movies where rating >= r
    public List<Movie> filterRating(int r) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getRating() >= r) {
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
    }

    //MODIFIES: this
    //EFFECTS: produces movies where title == s
    public Movie findMovie(String s) {
        for (Movie m : this.movieList) {
            if (m.getName().equals(s)) {
                return m;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: produces list of movies where rating == 0
    public List<Movie> getListOfUnwatched() {
        List<Movie> unwatched = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getRating() == 0) {
                unwatched.add(m);
            }
        }
        return unwatched;
    }

    //EFFECTS: converts all movies in movie list to string
    public String movieListToString() {
        String movieListString = new String();
        for (Movie m : this.movieList) {
            movieListString += m.movieToString() + "\n";
        }
        return movieListString;
    }

    //getter
    public List<Movie> getMovieList() {
        return this.movieList;
    }
}
