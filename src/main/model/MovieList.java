package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a List of Movies
public class MovieList implements Writable {
    private final List<Movie> listOfMovie;

    //EFFECTS: constructs an empty ArrayList that holds Movies
    public MovieList() {
        this.listOfMovie = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds Movie to MovieList, doesn't allow duplicates
    public void addMovieToList(Movie m) {
        if (!listOfMovie.contains(m)) {
            this.listOfMovie.add(m);
        }
    }

    //MODIFIES: this
    //EFFECTS: produces String of list of movies where category == s
    public List<Movie> filterCategory(String s) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            if (m.getCategory().equals(s)) {
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
    }


    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating >= r
    public List<Movie> filterRating(int r) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            if (m.getRating() >= r) {
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
    }

    //MODIFIES: this
    //EFFECTS: returns movie where title == s or null if not found
    public Movie findMovie(String s) {
        for (Movie m : this.listOfMovie) {
            if (m.getName().equals(s)) {
                return m;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating == 0
    public List<Movie> getListOfUnwatched() {
        List<Movie> unwatched = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            if (m.getRating() == 0) {
                unwatched.add(m);
            }
        }
        return unwatched;
    }

    //EFFECTS: converts all movies in movie list to string
    public String movieListToString() {
        String movieListString = "";
        for (Movie m : this.listOfMovie) {
            movieListString += "\n" + m.movieToString();
        }
        return movieListString + "\n";
    }

    //EFFECTS: converts movie list to list of string
    public List<String> movieListToListOfString() {
        List<String> stringList = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            stringList.add(m.movieToString());
        }
        return stringList;
    }

    //EFFECTS: converts List<Movie to type MoveList
    public MovieList listToMovieList(List<Movie> l) {
        MovieList movieList = new MovieList();
        for (Movie m : l) {
            movieList.addMovieToList(m);
        }
        return movieList;
    }

    //getter
    public List<Movie> getListOfMovie() {
        return this.listOfMovie;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        return json;
    }

    //EFFECTS: returns movies in this Movie List as a JSON array
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : listOfMovie) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}
