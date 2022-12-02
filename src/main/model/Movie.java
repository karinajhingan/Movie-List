package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a Movie
public class Movie implements Writable {
    private final String title;
    private final String category;
    private int rating; //[1-10], 0 means unwatched
    private Event event;


    //EFFECTS: Constructs a Movie with a title, category, and rating.
    public Movie(String title, String category) {
        this.title = title;
        this.category = category;
        rating = 0;

    }

    //REQUIRES: r is an integer [1,10]
    //MODIFIES: this
    //EFFECTS: sets the rating of the Movie
    public void setRating(int r) {
        rating = r;
        event = new Event("Rated: " + movieToString());
        EventLog.getInstance().logEvent(event);
    }


    //EFFECTS: converts movie to string format
    public String movieToString() {
        return "Movie: " + title + ", Category: " + category + ", Rating: " + rating;

    }

    //getter
    public String getName() {
        return title;

    }

    //getter
    public int getRating() {
        return rating;

    }

    //getter
    public String getCategory() {
        return category;
    }

    //EFFECTS: converts a movie to a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("category", category);
        json.put("rating", rating);
        return json;
    }
}

