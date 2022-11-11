/*
package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MovieListUI extends JPanel implements ListSelectionListener {
    private JList movieList;
    private DefaultListModel defaultListModel;

    private JButton addButton;
    private JTextField movie;
    private static final String ADD = "Add Movie";



    public MovieListUI() {
        super(new BorderLayout());

        defaultListModel = new DefaultListModel<>();

        movieList = new JList(defaultListModel);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieList.setSelectedIndex(0);
        movieList.addListSelectionListener(this);
        //movieList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(movieList);

        addButton = new JButton(ADD);
        AddListener = new Listen

        movie = new JTextField();



    }
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
*/
