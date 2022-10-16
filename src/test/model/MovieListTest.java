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
        List<Movie> listOfMovies = myMovieList.getMovieList();
        assertTrue(listOfMovies.isEmpty());
    }

    @Test
    void testAddMovieToEmptyList() {
        this.myMovieList.addMovieToList(movie1);
        List<Movie> listOfMovies = myMovieList.getMovieList();
        assertTrue(listOfMovies.contains(movie1));
        assertEquals(1, listOfMovies.size());
    }

    @Test
    void testAddThreeMoviesWithDuplicateToList() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        List<Movie> listOfMovies = myMovieList.getMovieList();
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

        List<Movie> listOfMovies = this.myMovieList.filterCategory("Thriller");
        assertEquals(2, listOfMovies.size());
        assertEquals(movie1, listOfMovies.get(0));
        assertEquals(movie2, listOfMovies.get(1));
    }

    @Test
    void testFilterCategoryNoMovies() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        List<Movie> listOfMovies = this.myMovieList.filterCategory("Horror");
        assertTrue(listOfMovies.isEmpty());
    }

    @Test
    void testFilterRating() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);
        this.movie2.setRating(9);

        List<Movie> listOfMovies = this.myMovieList.filterRating(7);
        assertTrue(listOfMovies.contains(movie4));
        assertTrue(listOfMovies.contains(movie2));
        assertEquals(2, listOfMovies.size());
    }

    @Test
    void testFilterRatingUnder() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        List<Movie> listOfMovies = this.myMovieList.filterRating(6);
        assertTrue(listOfMovies.contains(movie4));
        assertEquals(1, listOfMovies.size());
    }

    @Test
    void testFilterRatingOver() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        List<Movie> listOfMovies = this.myMovieList.filterRating(8);
        assertEquals(0, listOfMovies.size());
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
        assertEquals(null, movie);
    }

    @Test
    void testListOfUnwatched() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        List<Movie> listOfMovies = this.myMovieList.getListOfUnwatched();
        assertEquals(3, listOfMovies.size());
        assertFalse(listOfMovies.contains(movie4));
    }

    @Test
    void testMovieListToListOfString() {
        assertEquals("", myMovieList.movieListToString());

        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.movie3.setRating(7);

        assertEquals("Movie: Good Time, Category: Thriller, Rating: 0\n" +
                        "Movie: Nightcrawler, Category: Thriller, Rating: 0\n" +
                        "Movie: Tenet, Category: Sci-fi, Rating: 7\n",
                myMovieList.movieListToString());


    }

}
