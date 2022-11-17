package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MovieListUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int BORDER_GAP = 13;
    private MovieList ml;
    private Movie movie;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_DESTINATION = "./data/movieList.json";

    private JPanel listArea;
    private JList javaList;
    private DefaultListModel<String> listModel;

    private static final String ERROR_MSG = "Error: Could not find Movie";

    public MovieListUI() throws FileNotFoundException {
        super("Movie List");
        ml = new MovieList();
        jsonReader = new JsonReader(JSON_DESTINATION);
        jsonWriter = new JsonWriter(JSON_DESTINATION);
        listModel = new DefaultListModel<>();
        javaList = new JList<>(listModel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().setBackground(Color.DARK_GRAY);

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(BORDER_GAP, BORDER_GAP, BORDER_GAP, BORDER_GAP));
        setLayout(new BorderLayout());

        addAllButtons();
        addListArea();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    public void addListArea() {
        listArea = new JPanel();
        listArea.setLayout(new GridLayout(0,1));
        listArea.setSize(new Dimension(0,0));
        listArea.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT - (2 * BORDER_GAP)));
        add(listArea, BorderLayout.WEST);
        listArea.add(javaList);
    }

    public void addAllButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        buttonArea.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT - (2 * BORDER_GAP)));
        buttonArea.setBackground(Color.black);
        add(buttonArea, BorderLayout.EAST);

        buttonArea.add(new JButton(new AllMovieAction()));
        buttonArea.add(new JButton(new AddMovieAction()));
        buttonArea.add(new JButton(new RateMovieAction()));
        buttonArea.add(new JButton(new SearchAction()));
        buttonArea.add(new JButton(new CategoryAction()));
        buttonArea.add(new JButton(new RatingAction()));
        buttonArea.add(new JButton(new UnwatchedAction()));
        buttonArea.add(new JButton(new SaveAction()));
        buttonArea.add(new JButton(new LoadAction()));
    }

    //todo :all below
    private class AllMovieAction extends AbstractAction {

        AllMovieAction() {
            super("All Movies");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            listModel.clear();
            listModel.addAll(ml.movieListToListOfString());
        }
    }

    private class AddMovieAction extends AbstractAction {

        AddMovieAction() {
            super("Add Movie");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputTitle = JOptionPane.showInputDialog("Title:");
            String inputCategory = JOptionPane.showInputDialog("Category:");
            movie = new Movie(inputTitle, inputCategory);
            ml.addMovieToList(movie);

            listModel.clear();
            listModel.addAll(ml.movieListToListOfString());
        }
    }

    private class RateMovieAction extends AbstractAction {

        RateMovieAction() {
            super("Rate Movie");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputTitle = JOptionPane.showInputDialog("Enter movie title to rate:");
            movie = ml.findMovie(inputTitle);
            if (movie == null) {
                JOptionPane.showMessageDialog(null, ERROR_MSG);
            } else {
                String r = JOptionPane.showInputDialog("Enter rating (integer):");
                movie.setRating(Integer.parseInt(r));
            }
            listModel.clear();
            listModel.addAll(ml.movieListToListOfString());
        }
    }

    private class SearchAction extends AbstractAction {

        SearchAction() {
            super("Search");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputTitle = JOptionPane.showInputDialog("Enter movie title:");
            if (ml.findMovie(inputTitle) == null) {
                JOptionPane.showMessageDialog(null, ERROR_MSG);
            } else {
                listModel.clear();
                listModel.addElement(ml.findMovie(inputTitle).movieToString());
            }
        }
    }

    private class CategoryAction extends AbstractAction {

        CategoryAction() {
            super("Filter by Category");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputCategory = JOptionPane.showInputDialog("Enter category:");
            if (ml.filterCategory(inputCategory).isEmpty()) {
                JOptionPane.showMessageDialog(null, ERROR_MSG);
            } else {
                listModel.clear();
                listModel.addAll(ml.listToMovieList(ml.filterCategory(inputCategory)).movieListToListOfString());
            }
        }
    }

    private class RatingAction extends AbstractAction {

        RatingAction() {
            super("Filter by Rating");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputMinRating = JOptionPane.showInputDialog("Enter minimum rating:");
            int r = Integer.parseInt(inputMinRating);
            if (ml.filterRating(r).isEmpty()) {
                JOptionPane.showMessageDialog(null, ERROR_MSG);
            } else {
                listModel.clear();
                listModel.addAll(ml.listToMovieList(ml.filterRating(r)).movieListToListOfString());
            }
        }
    }

    private class UnwatchedAction extends AbstractAction {

        UnwatchedAction() {
            super("View Unwatched Movies");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (ml.getListOfUnwatched().isEmpty()) {
                JOptionPane.showMessageDialog(null, ERROR_MSG);
            } else {
                listModel.clear();
                listModel.addAll(ml.listToMovieList(ml.getListOfUnwatched()).movieListToListOfString());
            }
        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            try {
                jsonWriter.open();
                jsonWriter.write(ml);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Your Movie List has been saved");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Unable to save file");
            }
        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load your saved Movie List");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                ml = jsonReader.read();
                JOptionPane.showMessageDialog(null,"Loaded your Movie List from "
                        + JSON_DESTINATION + "\nSelect All Movies to view");
            } catch (IOException e) {
                System.out.println("Unable to read file");
            }
        }
    }


    //todo
    public static void main(String[] args) {
        try {
            new MovieListUI();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not find file");
        }
    }
}
