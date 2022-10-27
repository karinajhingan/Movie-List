package persistence;

import model.MovieList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
// Referenced https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

//Writes JSON representation of MovieList to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer for destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer, throws FileNotFound if it can't open/write destination file
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of MovieList to file
    public void write(MovieList ml) {
        JSONObject json = ml.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes file from string
    private void saveToFile(String json) {
        writer.print(json);
    }
}
