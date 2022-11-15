package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieListUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int BORDER_GAP = 13;
    private MovieList ml;
    private Movie movie;
    private MovieListApp mlApp;

    private JPanel listArea;
    private static JList javaList;
    private static DefaultListModel<String> listModel;

    public MovieListUI() {
        super("Movie List");
        ml = new MovieList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
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

        //DefaultListModel<String> listModel = new DefaultListModel<>();
        //javaList = new JList<>(listModel);
        //todo delete if works :) listArea.removeAll();
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
            MovieListUI.listModel.clear();
            MovieListUI.listModel.addAll(ml.movieListToListOfString());
            MovieListUI.javaList = new JList<>(listModel);

            //listArea.removeAll();
            //listArea.add(javaList);
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

            // todo delete commented listModel = new DefaultListModel<>();
            //todo what happens if we add a movie while weve got a a filtered list displayed, maybe removeAll()
            listModel.addAll(ml.movieListToListOfString());
            javaList = new JList<>(listModel);

            //listArea.removeAll();

            //listArea.add(javaList);
        }
    }

    private class RateMovieAction extends AbstractAction {

        RateMovieAction() {
            super("Rate Movie");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputTitle = JOptionPane.showInputDialog("Enter Movie Title to Rate:");
            movie = ml.findMovie(inputTitle);
            if (movie == null) {
                JOptionPane.showMessageDialog(null, "Error: Could not find Movie");
            } else {
                String r = JOptionPane.showInputDialog("Enter Rating:");
                movie.setRating(Integer.parseInt(r));
            }

            listModel = new DefaultListModel<>();
            listModel.addAll(ml.movieListToListOfString());
            javaList = new JList<>(listModel);

            listArea.removeAll();
            listArea.add(javaList);
        }
    }

    private class SearchAction extends AbstractAction {

        SearchAction() {
            super("Search");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputTitle = JOptionPane.showInputDialog("Enter Movie Title:");
            listModel = new DefaultListModel<>();
            listModel.addElement(ml.findMovie(inputTitle).movieToString());
            javaList = new JList<>(listModel);

            listArea.removeAll();
            listArea.add(javaList);
        }
    }

    private class CategoryAction extends AbstractAction {

        CategoryAction() {
            super("Filter by Category");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputCategory = JOptionPane.showInputDialog("Enter Category:");
            listModel = new DefaultListModel<>();
            listModel.addAll(ml.listToMovieList(ml.filterCategory(inputCategory)).movieListToListOfString());
            javaList = new JList<>(listModel);

            listArea.removeAll();
            listArea.add(javaList);
        }
    }

    private class RatingAction extends AbstractAction {

        RatingAction() {
            super("Filter by Rating");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String inputMinRating = JOptionPane.showInputDialog("Enter Minimum Rating:");
            int r = Integer.parseInt(inputMinRating);
            listModel = new DefaultListModel<>();
            listModel.addAll(ml.listToMovieList(ml.filterRating(r)).movieListToListOfString());
            javaList = new JList<>(listModel);

            listArea.removeAll();
            listArea.add(javaList);

        }
    }

    private class UnwatchedAction extends AbstractAction {

        UnwatchedAction() {
            super("View Unwatched Movies");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            listModel = new DefaultListModel<>();
            listModel.addAll(ml.listToMovieList(ml.getListOfUnwatched()).movieListToListOfString());
            javaList = new JList<>(listModel);

            listArea.removeAll();
            listArea.add(javaList);
        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            mlApp.saveToFile();
        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load your Saved Movie List");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            mlApp.doLoadMovieList();
        }
    }


    //todo
    public static void main(String[] args) {
        new MovieListUI();
    }
}
