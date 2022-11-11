package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieListUI2 extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField title;
    private JTextField rating;
    private JTextField category;
    private JButton addBtn;
    private JButton rateBtn;
    private JButton searchBtn;
    private JButton categoryBtn;
    private JButton ratingBtn;
    private JButton saveBtn;
    private JButton loadBtn;




    private MovieList ml;
    private Movie movie;

    public MovieListUI2() {
        super("Movie List");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        title = new JTextField(5);
        category = new JTextField(5);
        rating = new JTextField(5);
        addAllBtns();
        //add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void addAllBtns() {
        addBtn();
        rateBtn();
        searchBtn();
        categoryBtn();
        ratingBtn();
        saveBtn();
        loadBtn();
        add(title);
        add(addBtn);
        add(rateBtn);
        add(searchBtn);
        add(categoryBtn);
        add(ratingBtn);
        add(saveBtn);
        add(loadBtn);
    }

    //todo
    public void addBtn() {
        addBtn = new JButton("Add Movie");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);
    }

    //todo
    public void rateBtn() {
        rateBtn = new JButton("Rate movie");
        rateBtn.setActionCommand("rate");
        rateBtn.addActionListener(this);
    }

    //todo
    public void searchBtn() {
        searchBtn = new JButton("Search");
        searchBtn.setActionCommand("search");
        searchBtn.addActionListener(this);
    }

    //todo
    public void categoryBtn() {
        categoryBtn = new JButton("Filter by Category");
        categoryBtn.setActionCommand("category");
        categoryBtn.addActionListener(this);
    }

    //todo
    public void ratingBtn() {
        ratingBtn = new JButton("Filter by Rating");
        ratingBtn.setActionCommand("rating");
        ratingBtn.addActionListener(this);
    }

    //todo
    public void saveBtn() {
        saveBtn = new JButton("Save");
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);
    }

    //todo
    public void loadBtn() {
        loadBtn = new JButton("Load your saved Movie List");
        loadBtn.setActionCommand("load");
        loadBtn.addActionListener(this);
    }

    //todo
    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            movie = new Movie(title.toString(), category.toString());
            ml.addMovieToList(movie);
        } else {
            movie.setRating(0);
        }

    }

    //todo
    public static void main(String[] args) {
        new MovieListUI2();
    }
}
