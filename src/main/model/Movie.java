package model;

public class Movie {
    private String title;
    private String category;
    private int rating; //[1-10], 0 means unwatched


    //EFFECTS: Constructs a Movie with a title, category, and rating.
    public Movie(String title, String category) {
        this.title = title;
        this.category = category;
        rating = 0;

    }

    //MODIFIES: this
    //EFFECTS: sets the rating of the Movie
    void setRating(int r) {
        rating = r;
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
}

