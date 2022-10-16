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

        assertEquals("\nMovie: Good Time, Category: Thriller, Rating: 0" +
                "\nMovie: Nightcrawler, Category: Thriller, Rating: 0" + "\n", this.myMovieList.filterCategory("Thriller"));
    }

    @Test
    void testFilterCategoryNoMovies() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);

        //List<Movie> listOfMovies = this.myMovieList.filterCategory("Horror");
        assertEquals("\n", this.myMovieList.filterCategory("Horror"));
    }

    @Test
    void testFilterRating() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);
        this.movie2.setRating(9);

        assertEquals("\nMovie: Nightcrawler, Category: Thriller, Rating: 9" +
                "\nMovie: Gattaca, Category: Sci-fi, Rating: 7" + "\n", this.myMovieList.filterRating(7));
    }

    @Test
    void testFilterRatingUnder() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        assertEquals("\nMovie: Gattaca, Category: Sci-fi, Rating: 7" + "\n", this.myMovieList.filterRating(6));
    }

    @Test
    void testFilterRatingOver() {
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        assertEquals("\n", this.myMovieList.filterRating(8));
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
        this.myMovieList.addMovieToList(movie1);
        this.myMovieList.addMovieToList(movie2);
        this.myMovieList.addMovieToList(movie3);
        this.myMovieList.addMovieToList(movie4);
        this.movie4.setRating(7);

        assertEquals("\nMovie: Good Time, Category: Thriller, Rating: 0" +
                "\nMovie: Nightcrawler, Category: Thriller, Rating: 0" +
                "\nMovie: Tenet, Category: Sci-fi, Rating: 0" + "\n", this.myMovieList.getListOfUnwatched());
    }

    @Test
    void testMovieListToListOfString() {
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

}
