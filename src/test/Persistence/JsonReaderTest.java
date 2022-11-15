package Persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends JsonTest{

    @Test
    void testNoFileFound() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException wasn't thrown");
        } catch (IOException e) {
            //expected to be thrown
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieList.json");
        try {
            MovieList ml = reader.read();
            assertEquals(0, ml.getListOfMovie().size());
        } catch (IOException e) {
            fail("Issue reading empty file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMovieList.json");
        try {
            MovieList ml = reader.read();
            List<Movie> movies = ml.getListOfMovie();
            assertEquals(3, ml.getListOfMovie().size());
            checkMovie("Good Time", "Thriller", 0, movies.get(0));
            checkMovie("Nightcrawler", "Thriller", 8, movies.get(1));
            checkMovie("Tenet", "Sci-fi",0, movies.get(2));
        } catch (IOException e) {
            fail("Issue reading normal file");
        }
    }
}

