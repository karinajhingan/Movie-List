package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieListUI2 extends JFrame implements ActionListener {
    private JTextField title;
    private JTextField rating;
    private JTextField category;

    private JButton addBtn;
    private JButton rateBtn;
    private JButton searchBtn;
    private JButton categoryBtn;
    private JButton ratingBtn;
    private JButton unwatchedBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    private MovieList ml;
    private Movie movie;
    private MovieListApp mlApp;

    public MovieListUI2() {
        super("Movie List");
        ml = new MovieList();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        title = new JTextField(5);
        category = new JTextField(5);
        rating = new JTextField(5);
        addAllBtns();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void addAllBtns() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.EAST);

        addBtn();
        rateBtn();
        searchBtn();
        categoryBtn();
        ratingBtn();
        unwatchedBtn();
        saveBtn();
        loadBtn();
        buttonArea.add(addBtn);
        buttonArea.add(rateBtn);
        buttonArea.add(searchBtn);
        buttonArea.add(categoryBtn);
        buttonArea.add(ratingBtn);
        buttonArea.add(unwatchedBtn);
        buttonArea.add(saveBtn);
        buttonArea.add(loadBtn);
    }

    //todo
    public void addBtn() {
        addBtn = new JButton(new AddMovieAction());
    }

    //todo
    public void rateBtn() {
        rateBtn = new JButton(new RateMovieAction());
    }

    //todo
    public void searchBtn() {
        searchBtn = new JButton(new SearchAction());
    }

    //todo
    public void categoryBtn() {
        categoryBtn = new JButton(new CategoryAction());
    }

    //todo
    public void ratingBtn() {
        ratingBtn = new JButton(new RatingAction());
    }

    public void unwatchedBtn() {
        unwatchedBtn = new JButton(new UnwatchedAction());
    }

    //todo
    public void saveBtn() {
        saveBtn = new JButton(new SaveAction());
    }

    //todo
    public void loadBtn() {
        loadBtn = new JButton(new LoadAction());
    }

    //todo
    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Movie")) {
            String inputTitle = JOptionPane.showInputDialog("Title:");
            String inputCategory = JOptionPane.showInputDialog("Category:");
            movie = new Movie(inputTitle, inputCategory);
            ml.addMovieToList(movie);
        } else if (e.getActionCommand().equals("rate")) {
            String inputTitle = JOptionPane.showInputDialog("Enter movie title to rate:");
            movie = ml.findMovie(inputTitle);
            if (movie == null) {
                JOptionPane.showMessageDialog(null, "Error: Could not find movie");
            } else {
                String r = JOptionPane.showInputDialog("Enter rating:");
                movie.setRating(Integer.parseInt(r));
            }
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
            String inputTitle = JOptionPane.showInputDialog("Enter movie title to rate:");
            movie = ml.findMovie(inputTitle);
            if (movie == null) {
                JOptionPane.showMessageDialog(null, "Error: Could not find movie");
            } else {
                String r = JOptionPane.showInputDialog("Enter rating:");
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

        }
    }

    private class CategoryAction extends AbstractAction {

        CategoryAction() {
            super("Filter by category");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class RatingAction extends AbstractAction {

        RatingAction() {
            super("Filter by rating");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class UnwatchedAction extends AbstractAction {

        UnwatchedAction() {
            super("View unwatched(unrated) movies");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {


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
            super("Load your saved MovieList");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            mlApp.doLoadMovieList();
        }
    }


    //todo
    public static void main(String[] args) {
        new MovieListUI2();
    }
}
