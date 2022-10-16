package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    private Movie myMovie;

    @BeforeEach
    void runBefore() {
        myMovie = new Movie("Good Time", "Thriller");
    }

    @Test
    void testMovie() {
        assertEquals("Good Time", myMovie.getName());
        assertEquals("Thriller", myMovie.getCategory());
        assertEquals(0, myMovie.getRating());
    }

    @Test
    void testSetRating() {
        myMovie.setRating(2);
        assertEquals(2, myMovie.getRating());
    }

    @Test
    void testChangeRating() {
        myMovie.setRating(8);
        assertEquals(8, myMovie.getRating());
    }

    @Test
    void testMovieToString() {
        assertEquals("Movie: Good Time, Category: Thriller, Rating: 0", myMovie.movieToString());
        myMovie.setRating(8);
        assertEquals("Movie: Good Time, Category: Thriller, Rating: 8", myMovie.movieToString());
    }
}
