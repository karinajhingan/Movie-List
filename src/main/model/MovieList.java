package model;


import exception.DuplicateException;
import exception.NoMoviesFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a List of Movies
public class MovieList implements Writable {
    private final List<Movie> listOfMovie;
    private Event event;

    //EFFECTS: constructs an empty ArrayList that holds Movies
    public MovieList() {
        this.listOfMovie = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds Movie to MovieList, doesn't allow duplicates
    public void addMovieToList(Movie m) throws DuplicateException {
        boolean contains = false;
        for (Movie movie : listOfMovie) {
            contains = movie.getName().equals(m.getName());
            if (contains) {
                throw new DuplicateException(m.getName());
            }
        }
        this.listOfMovie.add(m);
        event = new Event("Added: " + m.movieToString());
        EventLog.getInstance().logEvent(event);
    }

    //MODIFIES: this
    //EFFECTS: produces String of list of movies where category == s
    public List<Movie> filterCategory(String s) throws NoMoviesFoundException {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            if (m.getCategory().equalsIgnoreCase(s)) {
                filteredMovieList.add(m);
            }
        }
        event = new Event("Filtered by category: " + s);
        EventLog.getInstance().logEvent(event);
        if (filteredMovieList.isEmpty()) {
            throw new NoMoviesFoundException("No " + s + " movies");
        } else {
            return filteredMovieList;
        }
    }


    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating >= r
    public List<Movie> filterRating(int r) throws NoMoviesFoundException {
        List<Movie> filteredMovieList = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            if (m.getRating() >= r) {
                filteredMovieList.add(m);
            }
        }
        Event event = new Event("Filtered by rating: " + r);
        EventLog.getInstance().logEvent(event);
        if (filteredMovieList.isEmpty()) {
            throw new NoMoviesFoundException("No movies are rated at least " + r);
        } else {
            return filteredMovieList;
        }
    }

    //MODIFIES: this
    //EFFECTS: returns movie where title == s or null if not found
    public Movie findMovie(String s) throws NoMoviesFoundException {
        Movie found = null;
        for (Movie m : this.listOfMovie) {
            if (m.getName().equalsIgnoreCase(s)) {
                found = m;
                return found;
            }
        }
        event = new Event("Searched for: " + s);
        EventLog.getInstance().logEvent(event);
        throw new NoMoviesFoundException("No results for '" + s + "'");
    }

    public Movie randomMovie() throws NoMoviesFoundException {
        this.getListOfUnwatched();
        int maximum = this.getListOfUnwatched().size();
        if (maximum == 0) {
            throw new NoMoviesFoundException("No movies");
        } else {
            int random = (int) ((Math.random() * maximum));
            return getListOfUnwatched().get(random);
        }
    }

    //MODIFIES: this
    //EFFECTS: produces String of list of movies where rating == 0
    public List<Movie> getListOfUnwatched() throws NoMoviesFoundException {
        List<Movie> unwatched = new ArrayList<>();
        for (Movie m : this.listOfMovie) {
            if (m.getRating() == 0) {
                unwatched.add(m);
            }
        }
        event = new Event("Filtered by unwatched");
        EventLog.getInstance().logEvent(event);
        if (unwatched.isEmpty()) {
            throw new NoMoviesFoundException("You have watched all of your movies");
        } else {
            return unwatched;
        }
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
            try {
                movieList.addMovieToList(m);
            } catch (DuplicateException e) {
                System.out.println("issue with duplicates in list");
            }
        }
        return movieList;
    }

    //getter
    public List<Movie> getListOfMovie() {
        return this.listOfMovie;
    }

    //EFFECTS: converts a MovieList to a Json Object
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
