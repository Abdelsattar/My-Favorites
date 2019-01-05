package com.sattar.myfavorites.Repositories;

import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sattar on 4-1-2019
 */
@RunWith(AndroidJUnit4.class)
public class MoviesRepositoryTest {

    private MovieRepository repository;
    Realm testRealm;
    Movie movie1, movie2;

    @Before
    public void setUp() throws Exception {
        repository = new MovieRepository();

        Realm.init(InstrumentationRegistry.getTargetContext());
        RealmConfiguration testConfig =
                new RealmConfiguration.Builder().
                        inMemory().
                        name("test-realm").build();
        testRealm = Realm.getInstance(testConfig);
    }

    @After
    public void tearDown() throws Exception {

        testRealm.executeTransaction(realm -> testRealm.deleteAll());
    }

    @Test
    public void insertMovies() {
        repository.insertMovies(
                testRealm,
                "X-Men",
                "a science fiction movie",
                "url",
                8.6);

        //verify that the movies already inserted
        assertEquals(1, testRealm.where(Movie.class).findAll().size());
    }

    @Test
    public void getAllMovies_EmptyDB() {

        //verify that the movies already inserted
        assertEquals(0, repository.getAllMovies(testRealm).size());
    }

    @Test
    public void getAllMovies() {

        testRealm.executeTransaction(realm -> {
            Movie movie = testRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName("X-Men");
            movie.setDescription("a science fiction movie");
            movie.setImagePath("url");
            movie.setRate(8.7);

            Movie movie2 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie2.setName("X-Men");
            movie2.setDescription("a science fiction movie");
            movie2.setImagePath("url");
            movie2.setRate(8.7);

            Movie movie3 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie3.setName("X-Men");
            movie3.setDescription("a science fiction movie");
            movie3.setImagePath("url");
            movie3.setRate(8.7);

            Movie movie4 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie4.setName("X-Men");
            movie4.setDescription("a science fiction movie");
            movie4.setImagePath("url");
            movie4.setRate(8.7);

        });

        //verify that the movies already inserted
        assertEquals(4, repository.getAllMovies(testRealm).size());
    }

    @Test
    public void isThereMovies_EmptyDB() {
        assertTrue(testRealm.isEmpty());
    }

    @Test
    public void isThereMovies() {
        testRealm.executeTransaction(realm -> {
            Movie movie = testRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName("X-Men");
            movie.setDescription("a science fiction movie");
            movie.setImagePath("url");
            movie.setRate(8.7);
        });
        assertFalse(testRealm.isEmpty());
    }

    @Test
    public void getMoviesByHighest() {

        testRealm.executeTransaction(realm -> {
            movie1 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie1.setName("X-Men");
            movie1.setDescription("a science fiction movie");
            movie1.setImagePath("url");
            movie1.setRate(9.5);

            movie2 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie2.setName("X-Men");
            movie2.setDescription("a science fiction movie");
            movie2.setImagePath("url");
            movie2.setRate(8.7);


        });
        List<Movie> movies = repository.getMoviesByHighest();

        assertEquals(movies.get(0),movie1);
        assertEquals(movies.get(1),movie2);
    }

    @Test
    public void orderMoviesByLowest() {

        testRealm.executeTransaction(realm -> {
            movie1 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie1.setName("X-Men");
            movie1.setDescription("a science fiction movie");
            movie1.setImagePath("url");
            movie1.setRate(7);

            movie2 = testRealm.createObject(Movie.class, Utils.generateUID());
            movie2.setName("X-Men");
            movie2.setDescription("a science fiction movie");
            movie2.setImagePath("url");
            movie2.setRate(8.7);


        });

        List<Movie> movies = repository.getMoviesByLowest();

        assertEquals(movies.get(0),movie1);
        assertEquals(movies.get(1),movie2);
    }
}