package persistence;

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
// Referenced https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

//Represents a reader that reads a movie list from JSON data in a file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader for the file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads from file and returns the movie list
    //throws IOException if error from reading the data from the file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    //EFFECTS: reads and returns the file that's been converted to string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses and returns Movie List from JSON Object
    // check input
    private MovieList parseMovieList(JSONObject jsonObject) {
        MovieList ml = new MovieList();
        addMovies(ml, jsonObject);
        return ml;
    }

    //MODIFIES: ml
    //EFFECTS: adds movies from JSON Object to Movie List
    private void addMovies(MovieList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(ml, nextMovie);
        }

    }

    //MODIFIES: ml
    //EFFECTS: adds a movie from JSONObject to MovieList
    private void addMovie(MovieList ml, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String category = jsonObject.getString("category");
        int rating = jsonObject.getInt("rating");
        Movie movie = new Movie(title, category);
        movie.setRating(rating);
        ml.addMovieToList(movie);
    }
}
