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
    //EFFECTS: produces String of list of movies where category == s
    public String filterCategory(String s) {
        String filteredMovieList = "";
        for (Movie m : this.movieList) {
            if (m.getCategory().equals(s)) {
                filteredMovieList += "\n" + m.movieToString();
            }
        }
        return filteredMovieList + "\n";
    }


    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating >= r
    public String filterRating(int r) {
        String filteredMovieList = "";
        for (Movie m : this.movieList) {
            if (m.getRating() >= r) {
                filteredMovieList += "\n" + m.movieToString();
            }
        }
        return filteredMovieList + "\n";
    }

    //MODIFIES: this
    //EFFECTS: returns movie where title == s or null if not found
    public Movie findMovie(String s) {
        for (Movie m : this.movieList) {
            if (m.getName().equals(s)) {
                return m;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating == 0
    public String getListOfUnwatched() {
        String unwatched = "";
        for (Movie m : this.movieList) {
            if (m.getRating() == 0) {
                unwatched += "\n" + m.movieToString();
            }
        }
        return unwatched + "\n";
    }

    //EFFECTS: converts all movies in movie list to string
    public String movieListToString() {
        String movieListString = "";
        for (Movie m : this.movieList) {
            movieListString += "\n" + m.movieToString();
        }
        return movieListString + "\n";
    }

    //getter
    public List<Movie> getMovieList() {
        return this.movieList;
    }
}
