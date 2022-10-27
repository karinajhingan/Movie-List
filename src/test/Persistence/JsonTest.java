package Persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title, String category, int rating, Movie movie) {
        assertEquals(title, movie.getName());
        assertEquals(category, movie.getCategory());
        assertEquals(rating, movie.getRating());

    }
}
