package ui;

import model.Movie;
import model.MovieList;

import java.util.List;
import java.util.Scanner;

public class MovieListApp {
    private Movie mo;
    private MovieList ml;
    private Scanner input;

    public MovieListApp() {
        runMovieList();
    }

    private void runMovieList() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            movieMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
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
        if (command.equals("add")) {
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
    private void doAddMovieToList() {
        System.out.print("Enter movie title to add: ");
        String title = input.next();
        System.out.print("Enter movie Category: ");
        String category = input.next();
        this.mo = new Movie(title, category);
        this.ml.addMovieToList(this.mo);
    }

    //MODIFIES: this
    //EFFECTS: conducts rating a movie
    private void doSetRating() {
        System.out.print("Enter movie title to rate: ");
        String title = input.next();
        if (null != this.ml.findMovie(title)) {
            Movie foundMovie = this.ml.findMovie(title);
            System.out.print("Enter rating (integer from 1-10): ");
            int r = input.nextInt();
            foundMovie.setRating(r);
        } else {
            System.out.print("Could not find movie");
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts searching a movie from the list
    private void doFindMovie() {
        String title = "";
        System.out.print("Enter title: ");
        title = input.next();
        Movie m = this.ml.findMovie(title);
        if (null != m) {
            System.out.print(m.movieToString());
        } else {
            System.out.print("Could not find movie");
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all movies in list that match the category
    private void doFilterCategory() {
        System.out.print("Enter category: ");
        String category = input.next();
        System.out.print(this.ml.filterCategory(category));
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all movies in list >= the rating
    private void doFilterRating() {
        System.out.print("Enter minimum rating (integer from 1-10): ");
        int r = input.nextInt();
        System.out.print(this.ml.filterRating(r));
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all unwatched movies in list
    private void doGetUnwatched() {
        System.out.print(this.ml.getListOfUnwatched());
    }

    //MODIFIES: this
    //EFFECTS: conducts displaying all movies in list
    private void doGetMovieList() {
        System.out.print(this.ml.movieListToString());
    }

}
