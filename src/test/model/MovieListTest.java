package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieListTest {
    private MovieList myMovieList;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Movie movie4;

    @BeforeEach
    void setUp() {
        this.myMovieList = new MovieList();
        this.movie1 = new Movie("Good Time", "Thriller");
        this.movie2 = new Movie("Nightcrawler", "Thriller");
        this.movie3 = new Movie("Tenet", "Sci-fi");
        this.movie4 = new Movie("Gattaca", "Sci-fi");
    }


    @Test
    void testEmptyList() {
        List<Movie> listOfMovies = myMovieList.getListOfMovie();
        assertTrue(listOfMovies.isEmpty());
    }

    @Test
    void testAddMovieToEmptyList() {
        this.myMovieList.addMovieToList(movie1);
        List<Movie> listOfMovies = myMovieList.getListOfMovie();
        assertTrue(listOfMovies.contains(movie1));
        assertEquals(1, listOfMovies.size());
    }

    @Test
    void testAddThreeMoviesWithDuplicateToList() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        List<Movie> listOfMovies = myMovieList.getListOfMovie();
        assertTrue(listOfMovies.contains(movie1));
        assertTrue(listOfMovies.contains(movie2));
        assertTrue(listOfMovies.contains(movie3));
        assertEquals(3, listOfMovies.size());
    }

    @Test
    void testFilterCategory() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        assertEquals(2, myMovieList.filterCategory("Thriller").size());
        assertTrue(myMovieList.filterCategory("Thriller").contains(movie1));
        assertTrue(myMovieList.filterCategory("Thriller").contains(movie2));
    }

    @Test
    void testFilterCategoryNoMovies() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        assertTrue(myMovieList.filterCategory("Horror").isEmpty());
    }


    @Test
    void testFilterRating() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie3.setRating(5);
        this.movie2.setRating(6);
        this.movie4.setRating(7);

        assertEquals(2, this.myMovieList.filterRating(6).size());
        assertTrue(myMovieList.filterRating(6).contains(movie4));
        assertTrue(myMovieList.filterRating(6).contains(movie2));
    }


    @Test
    void testFindMovie() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);

        Movie movie = this.myMovieList.findMovie("Nightcrawler");
        assertEquals(movie2, movie);
    }

    @Test
    void testFindMovieNoMovies() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);

        Movie movie = this.myMovieList.findMovie("Buffalo 66");
        assertNull(movie);
    }

    @Test
    void testListOfUnwatched() {
        assertTrue(myMovieList.getListOfUnwatched().isEmpty());
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        assertEquals(3, this.myMovieList.getListOfUnwatched().size());
        assertTrue(myMovieList.getListOfUnwatched().contains(movie1));
        assertTrue(myMovieList.getListOfUnwatched().contains(movie2));
        assertTrue(myMovieList.getListOfUnwatched().contains(movie3));
        assertFalse(myMovieList.getListOfUnwatched().contains(movie4));
    }

    @Test
    void testMovieListToString() {
        assertEquals("\n", myMovieList.movieListToString());

        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.movie3.setRating(7);

        assertEquals("\nMovie: Good Time, Category: Thriller, Rating: 0" +
                        "\nMovie: Nightcrawler, Category: Thriller, Rating: 0" +
                        "\nMovie: Tenet, Category: Sci-fi, Rating: 7" + "\n",
                myMovieList.movieListToString());
    }

    @Test
    void testMovieListToListOfString() {
        assertTrue(myMovieList.movieListToListOfString().isEmpty());

        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        assertEquals(3, myMovieList.movieListToListOfString().size());
    }

    @Test
    void testListToMovieList() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        assertEquals(movie1, myMovieList.listToMovieList(myMovieList.getListOfUnwatched()).findMovie(movie1.getName()));
        assertEquals(movie2, myMovieList.listToMovieList(myMovieList.getListOfUnwatched()).findMovie(movie2.getName()));
    }

}
