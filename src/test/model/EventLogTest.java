package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ui.MovieListUI.JSON_DESTINATION;

public class EventLogTest {
    private EventLog eventLog = EventLog.getInstance();
    private MovieList ml = new MovieList();
    private Movie m1;

    @BeforeEach
    void setUp() {
        eventLog.clear();
        m1 = new Movie("Interstellar", "Sci-fi");
        ml.addMovieToList(m1);
    }

    @Test
    void testEventAdd() {
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        assertEquals("Added: " + m1.movieToString(), iterator.next().getDescription());
    }

    @Test
    void testEventRate() {
        m1.setRating(8);
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Rated: " + m1.movieToString(), iterator.next().getDescription());
    }

    @Test
    void testEventSave() throws FileNotFoundException {
        JsonWriter jsonWriter = new JsonWriter(JSON_DESTINATION);
        jsonWriter.open();
        jsonWriter.write(ml);
        jsonWriter.close();
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Saved Movie List", iterator.next().getDescription());
    }

    @Test
    void testEventLoad() throws IOException {
        JsonReader jsonReader = new JsonReader(JSON_DESTINATION);
        jsonReader.read();
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Loaded Movie List", iterator.next().getDescription());
    }



    @Test
    void testEventFilterCategory() {
        String category = "Sci-fi";
        ml.filterCategory(category);
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Filtered by category: " + category, iterator.next().getDescription());
    }

    @Test
    void testEventFilterRating() {
        int rating = 7;
        ml.filterRating(rating);
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Filtered by rating: " + rating, iterator.next().getDescription());
    }

    @Test
    void testEventSearch() {
        String title = "Interstellar";
        ml.findMovie(title);
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Searched for: " + title, iterator.next().getDescription());
    }

    @Test
    void testEventUnwatched() {
        ml.getListOfUnwatched();
        Iterator<Event> iterator = eventLog.iterator();
        iterator.next();
        iterator.next();
        assertEquals("Filtered by unwatched", iterator.next().getDescription());
    }

}
