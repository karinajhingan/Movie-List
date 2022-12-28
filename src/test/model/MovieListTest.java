package model;

import exception.DuplicateException;
import exception.NoMoviesFoundException;
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
        myMovieList = new MovieList();
        movie1 = new Movie("Good Time", "Thriller");
        movie2 = new Movie("Nightcrawler", "Thriller");
        movie3 = new Movie("Tenet", "Sci-fi");
        movie4 = new Movie("Gattaca", "Sci-fi");
    }


    @Test
    void testEmptyList() {
        List<Movie> listOfMovies = myMovieList.getListOfMovie();
        assertTrue(listOfMovies.isEmpty());
    }

    @Test
    void testAddMovieToEmptyList() {
        try {
            myMovieList.addMovieToList(movie1);
        } catch (DuplicateException e) {
            throw new RuntimeException(e);
        }
        List<Movie> listOfMovies = myMovieList.getListOfMovie();
        assertTrue(listOfMovies.contains(movie1));
        assertEquals(1, listOfMovies.size());
    }

    @Test
    void testAddDuplicateToList() {
        try {
            myMovieList.addMovieToList(movie1);
            myMovieList.addMovieToList(movie2);
        } catch (DuplicateException e) {
            fail("Duplicate Exception was thrown but no duplicate");
        }
        try {
            myMovieList.addMovieToList(movie1);
            fail("Duplicate was added");
        } catch (DuplicateException e) {
            //success
        } finally {
            List<Movie> listOfMovies = myMovieList.getListOfMovie();
            assertTrue(listOfMovies.contains(movie1));
            assertTrue(listOfMovies.contains(movie2));
            assertEquals(2, listOfMovies.size());
        }
    }

    @Test
    void testFilterCategory() throws DuplicateException {
        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);

        try {
            assertEquals(2, myMovieList.filterCategory("Thriller").size());
            assertTrue(myMovieList.filterCategory("Thriller").contains(movie1));
            assertTrue(myMovieList.filterCategory("Thriller").contains(movie2));
        } catch (NoMoviesFoundException e) {
            fail("issue filtering movies");
        }

    }

    @Test
    void testFilterCategoryNoMovies() throws DuplicateException {
        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);

        try {
            myMovieList.filterCategory("Horror");
            fail("List is empty so exception should have been thrown");
        } catch (NoMoviesFoundException e) {
            //success
        }
    }


    @Test
    void testFilterRating() throws DuplicateException {
        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);
        myMovieList.addMovieToList(movie4);
        movie3.setRating(5);
        movie2.setRating(6);
        movie4.setRating(7);

        try {
            assertEquals(2, myMovieList.filterRating(6).size());
            assertTrue(myMovieList.filterRating(6).contains(movie4));
            assertTrue(myMovieList.filterRating(6).contains(movie2));
        } catch (NoMoviesFoundException e) {
            fail("issue filtering rating");
        }

    }


    @Test
    void testFindMovie() throws DuplicateException {
        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);
        myMovieList.addMovieToList(movie4);

        Movie movie = null;
        try {
            movie = myMovieList.findMovie("Nightcrawler");
        } catch (NoMoviesFoundException e) {
            fail("issue finding movie");
        } finally {
            assertEquals(movie2, movie);
        }
    }

    @Test
    void testFindMovieNoMovies() throws DuplicateException {
        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);
        myMovieList.addMovieToList(movie4);


        try {
            myMovieList.findMovie("Buffalo 66");
            fail("no movie found, should have thrown excpetion");
        } catch (NoMoviesFoundException e) {
            //success
        }

    }

    @Test
    void testListOfUnwatched() throws DuplicateException {
        try {
            myMovieList.getListOfUnwatched();
            fail("list is empty, should have thrown exception");
        } catch (NoMoviesFoundException e) {
            //success
        }
        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);
        myMovieList.addMovieToList(movie4);
        movie4.setRating(7);

        try {
            assertEquals(3, myMovieList.getListOfUnwatched().size());
            assertTrue(myMovieList.getListOfUnwatched().contains(movie1));
            assertTrue(myMovieList.getListOfUnwatched().contains(movie2));
            assertTrue(myMovieList.getListOfUnwatched().contains(movie3));
            assertFalse(myMovieList.getListOfUnwatched().contains(movie4));
        } catch (NoMoviesFoundException e) {
            fail("issue getting unwatched");
        }

    }

    @Test
    void testMovieListToString() throws DuplicateException {
        assertEquals("\n", myMovieList.movieListToString());

        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);
        movie3.setRating(7);

        assertEquals("\nMovie: Good Time, Category: Thriller, Rating: 0" +
                        "\nMovie: Nightcrawler, Category: Thriller, Rating: 0" +
                        "\nMovie: Tenet, Category: Sci-fi, Rating: 7" + "\n",
                myMovieList.movieListToString());
    }

    @Test
    void testMovieListToListOfString() throws DuplicateException {
        assertTrue(myMovieList.movieListToListOfString().isEmpty());

        myMovieList.addMovieToList(movie1);
        myMovieList.addMovieToList(movie2);
        myMovieList.addMovieToList(movie3);

        assertEquals(3, myMovieList.movieListToListOfString().size());
    }

    @Test
    void testListToMovieList() throws NoMoviesFoundException {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        assertEquals(movie1, myMovieList.listToMovieList(myMovieList.getListOfUnwatched()).findMovie(movie1.getName()));
        assertEquals(movie2, myMovieList.listToMovieList(myMovieList.getListOfUnwatched()).findMovie(movie2.getName()));
    }

    @Test void testRandomMovie() throws DuplicateException {
        this.myMovieList.addMovieToList(movie1);
    }

}
