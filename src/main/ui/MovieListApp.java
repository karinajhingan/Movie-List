package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Referenced https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

//Represents a Console Based Application
public class MovieListApp {
    private MovieList ml;
    private Movie movie;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_DESTINATION = "./data/movieList.json";

    public MovieListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        ml = new MovieList();
        jsonReader = new JsonReader(JSON_DESTINATION);
        jsonWriter = new JsonWriter(JSON_DESTINATION);
        runMovieList();
    }

    //EFFECTS: runs movieList
    private void runMovieList() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            movieMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.print("Would you like to save your movie list? (y/n):");
                String answer = input.next();
                if (answer.equals("y")) {
                    saveToFile();
                }
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("load")) {
            doLoadMovieList();
        } else if (command.equals("save")) {
            saveToFile();
        } else if (command.equals("add")) {
            doAddMovieToList();
        } else if (command.equals("rate")) {
            doSetRating();
        } else if (command.equals("search")) {
            doFindMovie();
        } else if (command.equals("category")) {
            doFilterCategory();
        } else if (command.equals("rating")) {
            doFilterRating();
        } else if (command.equals("unwatched")) {
            doGetUnwatched();
        } else if (command.equals("all")) {
            doGetMovieList();
        } else {
            System.out.println("Selection not valid");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes MovieList
    private void init() {
        this.ml = new MovieList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: displays menu of options to user
    private void movieMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tload -> Load your movie list");
        System.out.println("\tsave -> Save your movie list");
        System.out.println("\tadd -> Add Movie");
        System.out.println("\trate -> Rate Movie");
        System.out.println("\tsearch -> Search Movie List");
        System.out.println("\tcategory -> Filter by category");
        System.out.println("\trating -> Filter by rating");
        System.out.println("\tunwatched -> View unwatched movies");
        System.out.println("\tall -> View all movies");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: conducts adding a movie to the list
    public void doAddMovieToList() {
        System.out.print("Enter movie title to add: ");
        String title = input.next();
        System.out.print("Enter movie Category: ");
        String category = input.next();
        movie = new Movie(title, category);
        if (null != this.ml.findMovie(title)) {
            System.out.print("\nThis Movie is already in your list.\n");
        } else {
            this.ml.addMovieToList(movie);
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts rating a movie
    public void doSetRating() {
        System.out.print("Enter movie title to rate: ");
        String title = input.next();
        if (null != this.ml.findMovie(title)) {
            movie = this.ml.findMovie(title);
            System.out.print("Enter rating (integer from 1-10): ");
            int r = input.nextInt();
            movie.setRating(r);
        } else {
            System.out.print("\nCould not find movie.\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts searching a movie from the list
    public void doFindMovie() {
        String title;
        System.out.print("Enter title: ");
        title = input.next();
        movie = this.ml.findMovie(title);
        if (null != movie) {
            System.out.print(movie.movieToString());
        } else {
            System.out.print("\nCould not find movie.\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all movies in list that match the category
    public void doFilterCategory() {
        System.out.print("Enter category: ");
        String category = input.next().toLowerCase();
        if (this.ml.filterCategory(category).isEmpty()) {
            System.out.print("\nCould not find any " + category + " movies.");
        }
        System.out.print(ml.listToMovieList(this.ml.filterCategory(category)).movieListToString());
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all movies in list >= the rating
    public void doFilterRating() {
        System.out.print("Enter minimum rating (integer from 1-10): ");
        int r = input.nextInt();
        if (this.ml.filterRating(r).isEmpty()) {
            System.out.print("\nCould not find any movies with a rating of " + r + " or higher.\n");
        } else {
            System.out.print(ml.listToMovieList(this.ml.filterRating(r)).movieListToString());
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all unwatched movies in list
    public void doGetUnwatched() {
        if (this.ml.getListOfUnwatched().isEmpty()) {
            System.out.print("\nYou have watched all the movies in your list.\n");
        } else {
            System.out.print(ml.listToMovieList(this.ml.getListOfUnwatched()).movieListToString());
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all movies in list
    public void doGetMovieList() {
        if (this.ml.getListOfMovie().isEmpty()) {
            System.out.print("\nYour movie list is empty.\n");
        } else {
            System.out.print(this.ml.movieListToString());
        }
    }


    //EFFECTS: saved movie list to a file
    public void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(ml);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file");
        }
    }

    //MODIFIES: this
    //EFFECTS: load movie list from file
    public void doLoadMovieList() {
        try {
            ml = jsonReader.read();
            System.out.println("Loaded your movie list from " + JSON_DESTINATION);
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
    }
}
