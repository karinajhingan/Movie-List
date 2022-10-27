package Persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException wasn't thrown");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyFile() {
        try {
            MovieList ml = new MovieList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            ml = reader.read();
            assertEquals(0, ml.getMovieList().size());
        } catch (IOException e) {
            fail("Issue writing empty file");
        }
    }

    @Test
    void testWriterGeneralMovieList() {
        try {
            MovieList ml = new MovieList();
            Movie m1 = new Movie("Good Time", "Thriller");
            Movie m2 = new Movie("Nightcrawler", "Thriller");
            Movie m3 = new Movie("Tenet", "Sci-fi");
            m2.setRating(8);
            ml.addMovieToList(m1);
            ml.addMovieToList(m2);
            ml.addMovieToList(m3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
            ml = reader.read();
            List<Movie> movies = ml.getMovieList();
            assertEquals(3, ml.getMovieList().size());
            checkMovie("Good Time", "Thriller", 0, movies.get(0));
            checkMovie("Nightcrawler", "Thriller", 8, movies.get(1));
            checkMovie("Tenet", "Sci-fi",0, movies.get(2));
        } catch (IOException e) {
            fail("Issue reading normal file");
        }
    }
}
