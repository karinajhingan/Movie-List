package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


public class MovieList implements Writable {
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
    public List<Movie> filterCategory(String s) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getCategory().equals(s)) {
                filteredMovieList.add(m);
                //todo filteredMovieList += "\n" + m.movieToString();
            }
        }
        return filteredMovieList;
        //todo filteredMovieList + "\n";
    }


    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating >= r
    public List<Movie> filterRating(int r) {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getRating() >= r) {
                //todo filteredMovieList += "\n" + m.movieToString();
                filteredMovieList.add(m);
            }
        }
        return filteredMovieList;
                //todo filteredMovieList + "\n";
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
    public List<Movie> getListOfUnwatched() {
        List<Movie> unwatched = new ArrayList<>();
        for (Movie m : this.movieList) {
            if (m.getRating() == 0) {
                unwatched.add(m);
                //todo unwatched += "\n" + m.movieToString();
            }
        }
        return unwatched;
                //todo unwatched + "\n";
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        return json;
    }

    //EFFECTS: returns movies in this Movie List as a JSON array
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movieList) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}
