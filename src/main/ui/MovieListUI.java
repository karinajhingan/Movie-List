package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieListUI extends JFrame {
    private MovieList ml;
    private Movie movie;
    private MovieListApp mlApp;

    private JPanel listArea;
    private JList javaList;
    private DefaultListModel<Movie> listModel;

    public MovieListUI() {
        super("Movie List");
        ml = new MovieList();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        addAllButtons();
        addListArea();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /*super("Movie List");
    ml = new MovieList();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(400, 400));

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
    setLayout(new FlowLayout());

    addAllButtons();
    addListArea();
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);*/

    public void addListArea() {
        listArea = new JPanel();
        listArea.setLayout(new GridLayout(0,2));
        listArea.setSize(new Dimension(10,20));
        add(listArea, BorderLayout.WEST);
    }

    public void addAllButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
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
            listModel = new DefaultListModel<>();
            listModel.addAll(ml.getMovieList());
            javaList = new JList<>(listModel);
            listArea.removeAll();
            listArea.add(javaList);
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
            listModel.addElement(ml.findMovie(inputTitle));
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
            listModel.addAll(ml.filterCategory(inputCategory));
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
            listModel = new DefaultListModel<>();
            listModel.addAll(ml.filterRating(Integer.parseInt(inputMinRating)));
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
            listModel.addAll(ml.getListOfUnwatched());
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
